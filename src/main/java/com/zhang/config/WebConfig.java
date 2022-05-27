package com.zhang.config;

import com.zhang.interceptor.TokenInterceptor;
import com.zhang.model.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public TokenInterceptor authInterceptor() {
        return new TokenInterceptor();
    }

    @Bean
    public User currentUser() {
        return  new User();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        ArrayList<String> ignoreUrl = new ArrayList<>();
        ignoreUrl.add("/static/**");
        ignoreUrl.add("/api/user/login");
        ignoreUrl.add("/api/user/register");
        // 放行路径
        InterceptorRegistration registration = registry.addInterceptor(authInterceptor());
        registration.addPathPatterns("/api/**"); //所有路径都被拦截
        registration.excludePathPatterns(    //添加不拦截路径
                ignoreUrl
        );
    }
}
