//package com.bigff.blog.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("123");
//        registry.addInterceptor(new LoginInterceptor())
//        .addPathPatterns("/admin/**")
//        .excludePathPatterns("/login");
//
//    }
//}
