package com.zytd.account.books.config.security;

import com.zytd.account.books.common.base.LoginUserDetails;
import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.enums.VerificationCodeTypeEnum;
import com.zytd.account.books.model.Member;
import com.zytd.account.books.model.MemberVerificationCode;
import com.zytd.account.books.service.MemberExtendService;
import com.zytd.account.books.service.MemberService;
import com.zytd.account.books.service.MemberVerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 短信验证码处理类
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SmsUserDetailsServiceImpl implements UserDetailsService {
    private final MemberService memberService;
    private final MemberExtendService memberExtendService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member = memberService.selectByPhone(s);
        if(Objects.isNull(member))  {
            throw new UsernameNotFoundException("用户不存在!");
        }
        //查询短信验证码
        String verifyCode = memberExtendService.getVerifyCode(s, VerificationCodeTypeEnum.SMS.getType());
        return new LoginUserDetails(member, null,verifyCode);
    }
}
