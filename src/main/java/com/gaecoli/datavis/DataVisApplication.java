package com.gaecoli.datavis;

import com.gaecoli.datavis.interceptor.CorsInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("com.gaecoli.datavis.mapper")
public class DataVisApplication implements WebMvcConfigurer {

    @Resource
    CorsInterceptor corsInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(DataVisApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }

}
