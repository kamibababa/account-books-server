package com.zytd.account.books.config.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用于用户名密码验证码验证时密码的加密和匹配: 明文
 */
public class VerificationCodePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
