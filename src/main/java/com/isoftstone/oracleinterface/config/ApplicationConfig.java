package com.isoftstone.oracleinterface.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author wpf
 * @Date 2019/10/10 15:30
 */
@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    /**
     * MybatisPlus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
