package com.zytd.account.books.common.utils;

import com.zytd.account.books.common.base.MemberInfoVO;
import com.zytd.account.books.common.base.TokenVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_ID = "jti";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expire;

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public MemberInfoVO getMemberInfoFromToken(String token){
        Claims claims = getClaimsFromToken(token);
        if(Objects.isNull(claims))
            return null;
        if(claims.getExpiration().before(new Date()))
            return null;
        MemberInfoVO memberInfoVO = new MemberInfoVO(Long.valueOf(claims.getId()));
        return memberInfoVO;
    }

    /**
     * 生成token
     * @param memberInfoVO
     * @return
     */
    public TokenVO generateToken(MemberInfoVO memberInfoVO){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, memberInfoVO.getMemberId());
        String token = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + expire * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
        return new TokenVO(token);
    }

    /**
     * 根据token获取Claims
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
            log.info("token过期");
        } catch (Exception e) {
            log.info("token解析失败");
        }
        return claims;
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_ID, 2);
        String token = Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000)).signWith(SignatureAlgorithm.HS512, "yz-admin-secret").compact();
        System.out.println(token);
    }
}
