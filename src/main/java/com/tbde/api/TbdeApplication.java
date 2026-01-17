package com.tbde.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tbde")
public class TbdeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbdeApplication.class, args);
    }
}
