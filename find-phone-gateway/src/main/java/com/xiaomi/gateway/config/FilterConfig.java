package com.xm.gateway.config;


import com.xm.gateway.filter.MyReactorAegisFilter;
import com.xm.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**

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
