package com.bishe.teamproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(value = {"com.bishe.teamproject.dao","com.bishe.teamproject.mapper"})
public class TeamProjecyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamProjecyApplication.class, args);
    }

}
