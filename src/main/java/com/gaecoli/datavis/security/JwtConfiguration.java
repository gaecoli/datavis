package com.gaecoli.datavis.security;

import com.gaecoli.datavis.filter.LoginFilter;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final LoginFilter loginFilter;

    public JwtConfiguration(LoginFilter loginFilter) {
        this.loginFilter = loginFilter;
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
