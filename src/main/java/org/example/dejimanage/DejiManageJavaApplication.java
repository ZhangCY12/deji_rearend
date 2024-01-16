package org.example.dejimanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.dejimanage.mapper")
public class DejiManageJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(DejiManageJavaApplication.class, args);
    }

}
