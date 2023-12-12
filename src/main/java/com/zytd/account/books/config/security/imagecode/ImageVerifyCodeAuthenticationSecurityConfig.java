package com.zytd.account.books.config.security.imagecode;

import com.zytd.account.books.config.security.CustomAuthenticationFailureHandler;
import com.zytd.account.books.config.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 用户名密码添加图片验证码
 */
@Component
public class ImageVerifyCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) {
        //自定义ImageVerifyCodeAuthenticationFilter过滤器
        ImageVerifyCodeAuthenticationFilter imageVerifyCodeAuthenticationFilter = new ImageVerifyCodeAuthenticationFilter();
        imageVerifyCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        imageVerifyCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        imageVerifyCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        //设置自定义provider的认证器userDetailsService
        ImageVerifyCodeAuthenticationProvider authenticationProvider = new ImageVerifyCodeAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        //在UsernamePasswordAuthenticationFilter过滤前执行,执行完继续执行用户名和密码验证
        http.authenticationProvider(authenticationProvider)
        .addFilterAt(imageVerifyCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
