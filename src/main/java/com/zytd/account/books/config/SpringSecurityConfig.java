package com.zytd.account.books.config;

import com.zytd.account.books.config.security.*;
import com.zytd.account.books.config.security.imagecode.ImageVerifyCodeAuthenticationSecurityConfig;
import com.zytd.account.books.config.security.smscode.SmsVerifyCodeAuthenticationSecurityConfig;
import com.zytd.account.books.config.security.VerificationCodePasswordEncoder;
import com.zytd.account.books.config.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private SmsVerifyCodeAuthenticationSecurityConfig smsVerifyCodeAuthenticationSecurityConfig;
    @Autowired
    private ImageVerifyCodeAuthenticationSecurityConfig imageVerifyCodeAuthenticationSecurityConfig;
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

    /**
     * 禁用parentManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.parentAuthenticationManager(null);
//        super.configure(auth);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
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
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                // 短信验证码登录配置
                .apply(smsVerifyCodeAuthenticationSecurityConfig)
                .and()
                // 新增验证码登录
                .apply(imageVerifyCodeAuthenticationSecurityConfig)
                .and()
                // 登录认证失效
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointHandler);

        http.csrf().disable()   //关闭防火墙
                .addFilterAfter(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/member/getImageCode").permitAll()
                .antMatchers("/member/getVerifyCode").permitAll()  //放过不认证该接口
                .anyRequest().authenticated();  //其他所有接口都要认证
        super.configure(http);
    }
}
