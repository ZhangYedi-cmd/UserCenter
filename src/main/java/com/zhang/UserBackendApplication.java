package com.zhang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@MapperScan("com.zhang.mapper")
@SpringBootApplication
public class UserBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserBackendApplication.class, args);
    }

}
