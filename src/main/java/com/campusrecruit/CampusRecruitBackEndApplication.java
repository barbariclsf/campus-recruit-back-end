package com.campusrecruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.campusrecruit.mapper")
public class CampusRecruitBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusRecruitBackEndApplication.class, args);
    }

}
