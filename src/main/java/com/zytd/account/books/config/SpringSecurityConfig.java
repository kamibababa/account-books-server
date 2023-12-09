package com.zytd.account.books.config;

import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.config.security.AuthenticationEntryPointHandler;
import com.zytd.account.books.config.security.CustomAuthenticationFailureHandler;
import com.zytd.account.books.config.security.CustomAuthenticationSuccessHandler;
import com.zytd.account.books.config.security.SmsVerifyCodeAuthenticationSecurityConfig;
import com.zytd.account.books.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private SmsVerifyCodeAuthenticationSecurityConfig smsVerifyCodeAuthenticationSecurityConfig;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CacheUtil cacheUtil;
    @Value("${spring.session.timeout:1800}")
    private Integer timeOut;
    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new VerificationCodePasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()    //表单提交
//                .loginPage("/login.html")   //自定义登录界面
//                .loginProcessingUrl("/login")   //调用的登录接口
//                .successForwardUrl("/toMain");   //跳转到自定义登录成功界面，需要是post请求
        // 登录处理相关
        http.formLogin()
                // 登录成功或失败
                .successHandler(new CustomAuthenticationSuccessHandler(jwtTokenUtil,cacheUtil,timeOut))
                .failureHandler(new CustomAuthenticationFailureHandler())
                .and()
                .apply(smsVerifyCodeAuthenticationSecurityConfig)
                .and()
                // session失效
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler);

        http.csrf().disable()   //关闭防火墙
                .addFilterAfter(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/member/getVerifyCode").permitAll()  //放过不认证该接口
                .anyRequest().authenticated();  //其他所有接口都要认证
        super.configure(http);
    }
}
