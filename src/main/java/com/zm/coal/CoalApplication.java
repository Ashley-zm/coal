package com.zm.coal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
@MapperScan("com.zm.coal.mapper")
public class CoalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoalApplication.class, args);
    }

}
