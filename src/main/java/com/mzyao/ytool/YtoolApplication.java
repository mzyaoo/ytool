package com.mzyao.ytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@SpringBootApplication(exclude = {
//        DruidDataSourceAutoConfigure.class,
//        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
//})
@EnableCaching
@SpringBootApplication
public class YtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtoolApplication.class, args);
    }

}
