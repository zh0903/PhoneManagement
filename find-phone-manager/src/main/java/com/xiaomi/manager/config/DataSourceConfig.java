package com.xiaomi.manager.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author LYJ
 * @Description 配置druid连接池
 * @date 2021 年 02 月 20 日 19:38
 */
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }

//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//
//        Map<String,String> initParameters = new HashMap<>();
//        //设置druid后台管理名
//        initParameters.put("loginUsername","admin");
//        //设置druid后台密码
//        initParameters.put("loginPassword","123");
//        //设置白名单
//        initParameters.put("allow","");
//        //设置给名单，当白名单和黑名单同时存在时，黑名单优先
//        initParameters.put("deny","192.168.42.82");
//        //将配置参数map加载到InitParameters中
//        registrationBean.setInitParameters(initParameters);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean s(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new WebStatFilter());
//
//        Map<String,String> initParameters = new HashMap<>();
//        //设置要排除的请求
//        initParameters.put("exclusions","*.js,*.css,*.html,/druid/*");
//
//        registrationBean.setInitParameters(initParameters);
//
//        registrationBean.setUrlPatterns(Arrays.asList("/*"));
//        return registrationBean;
//    }
}
