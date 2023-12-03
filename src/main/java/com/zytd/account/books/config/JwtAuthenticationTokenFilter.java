package com.zytd.account.books.config;

import com.zytd.account.books.common.base.LoginUserDetails;
import com.zytd.account.books.common.base.MemberInfoVO;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.common.utils.RedisUtil;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.model.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("token");
            if(StringUtils.isBlank(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            //解析token
            MemberInfoVO vo = jwtTokenUtil.getMemberInfoFromToken(token);
            if(Objects.isNull(vo))  throw new RuntimeException("token非法");
            //判断是否已登出
            String value = redisUtil.getStringValue(CommonConstants.token_prefix + vo.getMemberId());
            if(StringUtils.isBlank(value))  throw new RuntimeException("用户已登出，请重新登录");
            Member member = new Member();
            member.setId(vo.getMemberId());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new LoginUserDetails(member, null), null, null));

            ThreadLocalUtil.MEMBER_ID_HOLDER.set(vo.getMemberId());
            filterChain.doFilter(request, response);
        }finally {
            ThreadLocalUtil.MEMBER_ID_HOLDER.remove();
        }
    }
}
