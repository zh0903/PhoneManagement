package com.xiaomi.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 26 日 下午3:22
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class FindPhoneManagerMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(FindPhoneManagerMainApplication.class, args);
    }
}
