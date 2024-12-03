package com.gaecoli.datavis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gaecoli.datavis.mapper")
public class DataVisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataVisApplication.class, args);
    }

}
