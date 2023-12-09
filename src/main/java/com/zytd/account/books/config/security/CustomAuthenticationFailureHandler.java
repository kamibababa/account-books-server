package com.zytd.account.books.config.security;

import com.alibaba.fastjson.JSON;
import com.zytd.account.books.common.base.ResponseCodeEnum;
import com.zytd.account.books.common.base.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ResultVO<String> resultVO = new ResultVO<>(e.getMessage(), ResponseCodeEnum.LOGIN_FAIL);
        out.write(JSON.toJSONString(resultVO));
        out.flush();
        out.close();
    }
}
