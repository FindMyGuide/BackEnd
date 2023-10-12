package com.find_my_guide.main_member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String ALLOWED_METHOD_NAMES = "GET, HEAD, POST, PUT, DELETE, PATCH";
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("https://find-my-guide.site", "http://13.58.216.103:3000",
                        "http://localhost:3000")
                .allowedMethods((ALLOWED_METHOD_NAMES.split(",")));
    }


//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
//                        .allowedOrigins("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
}