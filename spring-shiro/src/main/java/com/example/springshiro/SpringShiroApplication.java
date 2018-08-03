package com.example.springshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringShiroApplication extends SpringBootServletInitializer {

    //在启动类添加selvelt支持，需要继承SpringBootServletInitializer来重载configure方法
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringShiroApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringShiroApplication.class, args);
    }
}
