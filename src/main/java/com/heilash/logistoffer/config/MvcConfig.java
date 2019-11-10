package com.heilash.logistoffer.config;

import com.heilash.logistoffer.infrastructure.CommonData;
import com.heilash.logistoffer.infrastructure.CommonDataEnhancer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private CommonData commonData;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonDataEnhancer(commonData)).order(Ordered.HIGHEST_PRECEDENCE);
    }
}