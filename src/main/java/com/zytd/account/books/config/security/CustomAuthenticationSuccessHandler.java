package com.zytd.account.books.config.security;

import com.alibaba.fastjson.JSON;
import com.zytd.account.books.common.base.*;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.vo.member.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功处理器
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private JwtTokenUtil jwtTokenUtil;

    private CacheUtil cacheUtil;

    private Integer timeOut;

    public CustomAuthenticationSuccessHandler(JwtTokenUtil jwtTokenUtil, CacheUtil cacheUtil, Integer timeOut){
        this.jwtTokenUtil = jwtTokenUtil;
        this.cacheUtil = cacheUtil;
        this.timeOut = timeOut == null ? 1800:timeOut;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
        LoginUserDetails userDetails = (LoginUserDetails)authentication.getDetails();
        //生成token
        TokenVO tokenVO = jwtTokenUtil.generateToken(new MemberInfoVO(userDetails.getMember().getId()));
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(userDetails.getMember(), memberVO);
        memberVO.setMemberId(userDetails.getMember().getId());
        memberVO.setToken(tokenVO.getToken());
        // 存储token
		cacheUtil.setValue(CommonConstants.token_prefix + memberVO.getMemberId(), memberVO.getToken(),timeOut);

        resp.setContentType("application/json;charset=utf-8");
        ResultVO<String> resultVO = new ResultVO<>( ResponseCodeEnum.LOGIN_SUCCESS,JSON.toJSONString(memberVO));
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(resultVO));
        out.flush();
        out.close();
    }
}
