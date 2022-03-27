package com.xiaomi.gateway.config;


import com.xiaomi.gateway.filter.MyReactorAegisFilter;
import com.xiaomi.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LYJ
 * @Description
 * @date 2021 年 10 月 28 日 下午4:52
 */
@Configuration
public class FilterConfig {

    @Autowired
    private UserService userService;

    @Bean
    public GlobalFilter tokenFilter(){
        return new MyReactorAegisFilter(userService);
    }
}
