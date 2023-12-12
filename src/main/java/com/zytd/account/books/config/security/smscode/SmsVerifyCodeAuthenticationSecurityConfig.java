package com.zytd.account.books.config.security.smscode;

import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.config.security.CustomAuthenticationFailureHandler;
import com.zytd.account.books.config.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsVerifyCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) {
        //自定义SmsVerifyCodeAuthenticationFilter过滤器
        SmsVerifyCodeAuthenticationFilter smsVerifyCodeAuthenticationFilter = new SmsVerifyCodeAuthenticationFilter();
        smsVerifyCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsVerifyCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        smsVerifyCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        //设置自定义SmsVerifyCodeAuthenticationProvider的认证器userDetailsService
        SmsVerifyCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsVerifyCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsVerifyCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
