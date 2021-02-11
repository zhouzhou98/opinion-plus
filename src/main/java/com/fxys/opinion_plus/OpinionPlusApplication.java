package com.fxys.opinion_plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.fxys.opinion_plus.mapper")
@EnableAsync
public class OpinionPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpinionPlusApplication.class, args);
    }

}
