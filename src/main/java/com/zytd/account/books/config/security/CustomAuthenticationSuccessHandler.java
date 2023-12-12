package com.zytd.account.books.config.security;

import com.alibaba.fastjson.JSON;
import com.zytd.account.books.common.base.*;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.enums.VerificationCodeTypeEnum;
import com.zytd.account.books.model.Member;
import com.zytd.account.books.model.MemberVerificationCode;
import com.zytd.account.books.service.MemberService;
import com.zytd.account.books.vo.member.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 登录成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private JwtTokenUtil jwtTokenUtil;

    private CacheUtil cacheUtil;

    private Integer timeout;

    private MemberService memberService;

    public CustomAuthenticationSuccessHandler(JwtTokenUtil jwtTokenUtil, CacheUtil cacheUtil
            , @Value("${spring.session.timeout:1800}") Integer timeout, MemberService memberService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.cacheUtil = cacheUtil;
        this.timeout = timeout == null ? 1800 : timeout;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        // 查询用户信息
        String username;
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails =  (UserDetails)authentication.getPrincipal();
            username = userDetails.getUsername();
        } else {
            username =  (String) authentication.getPrincipal();
        }
        // 判断登录方式（短信：smscode；其他）
        Member member = memberService.selectByPhone(username);
        if (Objects.isNull(member)) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        //生成token
        TokenVO tokenVO = jwtTokenUtil.generateToken(new MemberInfoVO(member.getId()));
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        memberVO.setMemberId(member.getId());
        memberVO.setToken(tokenVO.getToken());
        // 存储token
        cacheUtil.setValue(CommonConstants.token_prefix + memberVO.getMemberId(), memberVO.getToken(), timeout);
        // 返回token
        resp.setContentType("application/json;charset=utf-8");
        ResultVO<String> resultVO = new ResultVO<>(ResponseCodeEnum.LOGIN_SUCCESS, JSON.toJSONString(memberVO));
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(resultVO));
        out.flush();
        out.close();
    }
}
