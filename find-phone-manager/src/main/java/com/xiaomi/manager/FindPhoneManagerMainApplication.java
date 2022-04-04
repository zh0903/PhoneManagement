package com.xm.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FindPhoneManagerMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(FindPhoneManagerMainApplication.class, args);
    }
}
