package com.zytd.account.books.config.security.imagecode;

import com.zytd.account.books.config.security.CustomAuthenticationFailureHandler;
import com.zytd.account.books.config.security.CustomAuthenticationSuccessHandler;
import com.zytd.account.books.config.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 用户名密码添加图片验证码
 */
@Component
public class ImageVerifyCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        // 替换掉UsernamePasswordAuthenticationFilter
        http.authenticationProvider(authenticationProvider)
        .addFilterAt(imageVerifyCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }
}
