package com.ly.apipassenger.interceptor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: liuqianyi
 * @Date: 2023/9/25 - 09 - 25 - 9:46
 * @Description: com.ly.apipassenger.interceptor
 * @version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())

        //拦截的
        .addPathPatterns("/**")
        //不拦截的
        .excludePathPatterns("/noauthText")
        .excludePathPatterns("/verification-code")
        .excludePathPatterns("/verification-code-check")
        .excludePathPatterns("/token-refresh");
    }
}
