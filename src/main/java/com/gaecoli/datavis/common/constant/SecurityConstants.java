package com.gaecoli.datavis.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/auth/login";

    public static String JWT_SECRET_KEY;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "security";
    public static final String TOKEN_AUDIENCE = "security-all";

    public static long EXPIRED_TIME;

    @Value("${security.jwt.secret.key}")
    public void setJwtSecretKey(String secret) {
        JWT_SECRET_KEY = secret;
    }

    @Value("${security.jwt.expired.time}")
    public void setExpiredTime(long time) {
        EXPIRED_TIME = time;
    }

    public static String getJwtSecretKey() {
        return JWT_SECRET_KEY;
    }

    public static long getExpiredTime() {
        return EXPIRED_TIME;
    }
}
