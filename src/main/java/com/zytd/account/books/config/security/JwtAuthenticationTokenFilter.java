package com.zytd.account.books.config.security;

import com.zytd.account.books.common.base.LoginUserDetails;
import com.zytd.account.books.common.base.MemberInfoVO;
import com.zytd.account.books.common.constants.CommonConstants;
import com.zytd.account.books.common.utils.CacheUtil;
import com.zytd.account.books.common.utils.JwtTokenUtil;
import com.zytd.account.books.common.utils.ThreadLocalUtil;
import com.zytd.account.books.model.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
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
    private final CacheUtil cacheUtil;
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;
    @Value("${spring.session.timeout:1800}")
    private Integer timeout;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("token");
            if(StringUtils.isBlank(token)) {
                authenticationEntryPointHandler.commence(request,response,new AccountExpiredException("token不存在"));
                return;
            }
            //解析token
            MemberInfoVO vo = jwtTokenUtil.getMemberInfoFromToken(token);
            if(Objects.isNull(vo))  {
                authenticationEntryPointHandler.commence(request,response,new AccountExpiredException("token非法"));
                return;
            }
            //判断是否已登出
            String value = cacheUtil.getStringValue(CommonConstants.token_prefix + vo.getMemberId());
            if(!StringUtils.isBlank(value))  {
                // 设置token上下文
                onRefreshToken(vo.getMemberId());
                Member member = new Member();
                member.setId(vo.getMemberId());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new LoginUserDetails(member, null), null, null));
                ThreadLocalUtil.MEMBER_ID_HOLDER.set(vo.getMemberId());
            }
            filterChain.doFilter(request, response);
        }finally {
            ThreadLocalUtil.MEMBER_ID_HOLDER.remove();
        }
    }

    /**
     * 刷新token策略：每次访问接口时刷新
     *
     * @param memberId
     */
    private void onRefreshToken(Long memberId) {
        String value = cacheUtil.getStringValue(CommonConstants.token_prefix + memberId);
        cacheUtil.setValue(CommonConstants.token_prefix + memberId,value,timeout);
    }
}
