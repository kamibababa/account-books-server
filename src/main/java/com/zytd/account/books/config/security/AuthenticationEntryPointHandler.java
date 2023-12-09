package com.zytd.account.books.config.security;

import com.alibaba.fastjson.JSON;
import com.zytd.account.books.common.base.ResponseCodeEnum;
import com.zytd.account.books.common.base.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 访问失败时，返回错误JSON，退回登录页
 */
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ResultVO<String> resultVO = new ResultVO<>("认证失效", ResponseCodeEnum.AUTH_FAIL);
        out.write(JSON.toJSONString(resultVO));
        out.flush();
        out.close();
    }
}
