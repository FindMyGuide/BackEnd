package com.find_my_guide.main_member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("https://find-my-guide.site", "http://13.58.216.103:3000",
                        "http://localhost:3000")
                .allowedMethods("GET", "PATCH", "POST", "PUT", "DELETE");
    }
}