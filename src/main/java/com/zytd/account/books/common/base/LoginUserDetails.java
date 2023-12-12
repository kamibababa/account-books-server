package com.zytd.account.books.common.base;

import com.zytd.account.books.common.enums.YesOrNoEnum;
import com.zytd.account.books.model.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Data
@Component
public class LoginUserDetails implements UserDetails {

    private Member member;

    private String password;

    private String verifyCode;

    public LoginUserDetails() {
    }

    public LoginUserDetails(Member member, String password) {
        this.member = member;
        this.password = password;
    }

    public LoginUserDetails(Member member, String password, String verifyCode) {
        this.member = member;
        this.password = password;
        this.verifyCode = verifyCode;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return member.getPhone();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return YesOrNoEnum.NO.getCode().equals(member.getIsForbidden());
    }
}
