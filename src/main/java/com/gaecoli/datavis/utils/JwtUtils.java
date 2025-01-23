package com.gaecoli.datavis.utils;

import com.gaecoli.datavis.common.constant.SecurityConstants;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import javax.xml.bind.DatatypeConverter;
import java.util.Collections;
import java.util.Date;

public class JwtUtils {

    private static final byte[] secretKey = DatatypeConverter.parseBase64Binary(SecurityConstants.getJwtSecretKey());

    private JwtUtils() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 生成token
     *
     * @param username 用户名
     * @return 返回生成的token
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(username)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setIssuedAt(new Date())
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.getExpiredTime() * 1000))
                .compact();
    }

    /**
     * 验证token是否有效
     *
     * @param token token信息
     * @return 如果返回true，说明token有效
     */
    public static boolean validateToken(String token) {
        try {
            getTokenBody(token);
            return true;
        } catch (ExpiredJwtException e) {
//            logger.warn("Token已过期: {} failed : {}", token, e.getMessage());
        } catch (UnsupportedJwtException e) {
//            logger.warn("不支持的Token: {} failed : {}", token, e.getMessage());
        } catch (MalformedJwtException e) {
//            logger.warn("无效的Token: {} failed : {}", token, e.getMessage());
        } catch (IllegalArgumentException e) {
//            logger.warn("Token为空: {} failed : {}", token, e.getMessage());
        }
        return false;
    }

    /**
     * 根据token获取认证信息
     *
     * @param token token信息
     * @return 返回用户认证信息
     */
    public static Authentication getAuthentication(String token) {
        Claims claims = getTokenBody(token);
        String username = claims.getSubject();

        // 创建一个基础的Authentication对象，不包含角色信息
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.emptyList()  // 空的权限列表
        );
    }

    /**
     * 从token中获取用户名
     *
     * @param token token信息
     * @return 返回用户名
     */
    public static String getUsernameFromToken(String token) {
        return getTokenBody(token).getSubject();
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}