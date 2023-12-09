package com.zytd.account.books.config.security;

import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsVerifyCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CacheUtil cacheUtil;
    @Value("${spring.session.timeout:1800}")
    private Integer timeOut;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义SmsVerifyCodeAuthenticationFilter过滤器
        SmsVerifyCodeAuthenticationFilter smsVerifyCodeAuthenticationFilter = new SmsVerifyCodeAuthenticationFilter();
        smsVerifyCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsVerifyCodeAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());
        smsVerifyCodeAuthenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler(jwtTokenUtil,cacheUtil,timeOut));
        //设置自定义SmsVerifyCodeAuthenticationProvider的认证器userDetailsService
        SmsVerifyCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsVerifyCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsVerifyCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
