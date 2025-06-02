package com.mzyao.ytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {
//        DruidDataSourceAutoConfigure.class,
//        DataSourceAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
//})
@SpringBootApplication
public class YtoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtoolApplication.class, args);
    }

}
