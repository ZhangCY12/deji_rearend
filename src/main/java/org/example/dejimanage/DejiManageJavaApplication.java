package org.example.dejimanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@MapperScan("org.example.dejimanage.mapper")
@EnableCaching  //支持redis缓存
@EnableScheduling  //支持定时任务
public class DejiManageJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(DejiManageJavaApplication.class, args);
    }

}
