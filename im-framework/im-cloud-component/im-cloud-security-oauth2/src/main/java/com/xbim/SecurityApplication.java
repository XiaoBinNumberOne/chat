package com.xbim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xbim.mapper")
public class SecurityApplication {


    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }
}
