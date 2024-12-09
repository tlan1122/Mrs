package com.example.demo.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 创建一个新的CorsConfiguration实例
        CorsConfiguration configuration = new CorsConfiguration();
        // 允许任何域名使用
        configuration.addAllowedOrigin("*");
        // 允许任何头
        configuration.addAllowedHeader("*");
        // 允许任何方法（post、get等）
        configuration.addAllowedMethod("*");

        // 创建一个新的UrlBasedCorsConfigurationSource实例
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 为所有的URL配置CorsConfiguration
        source.registerCorsConfiguration("/**", configuration);

        // 返回一个新的CorsFilter实例
        return new CorsFilter(source);
    }
}