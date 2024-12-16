package com.gaecoli.datavis.filter;

import com.gaecoli.datavis.common.constant.SecurityConstants;
import com.gaecoli.datavis.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

public class LoginFilter extends BasicAuthenticationFilter {
    public LoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 从 HTTP 请求中获取 token
        String token = this.getTokenFromHttpRequest(request);
        // 验证 token 是否有效
        if (StringUtils.isNotBlank(token) && JwtUtils.validateToken(token)) {
            // 获取认证信息
            Authentication authentication = JwtUtils.getAuthentication(token);
            // 将认证信息存入 Spring 安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 放行请求
        filterChain.doFilter(request, response);

    }

    /**
     * 从 HTTP 请求中获取 token
     *
     * @param request HTTP 请求
     * @return 返回 token
     */
    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (authorization == null || !authorization.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return null;
        }
        // 去掉 token 前缀
        return authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
    }
}
