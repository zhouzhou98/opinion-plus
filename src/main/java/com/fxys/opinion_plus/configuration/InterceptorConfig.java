package com.fxys.opinion_plus.configuration;

import com.fxys.opinion_plus.constants.PathConstants;
import com.fxys.opinion_plus.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author :suyuhzou
 * @date :20201214
 * @description 登录拦截器
 * @email :jack.su@jodoinc.com
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(PathConstants.USER_SING_IN).excludePathPatterns(PathConstants.USER_REGISTER)
                .excludePathPatterns("/index");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }


}
