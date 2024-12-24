package com.gaecoli.datavis.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class CorsInterceptor implements HandlerInterceptor {
    private static final List<Pattern> allowOrigins = Collections.singletonList(Pattern.compile(".*\\.solee\\.com(\\:[0-9]*)?$"));

    private boolean checkHost(String origin) {
        if (StringUtils.isNoneEmpty(origin)) {
            for (Pattern p : allowOrigins) {
                if (p.matcher(origin).matches()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        if (checkHost(request.getHeader("origin"))) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, HEAD, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-token, Authorization, x-xsrf-token, token");
        }

        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return false;
        }

        return true;
    }
}
