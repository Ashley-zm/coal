package com.zm.coal.config;

import com.zm.coal.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 2. 创建配置工具类。告诉拦截器我们拦截哪些请求。
 * 记得该类一定要加@Configuration注解
 *
 * @Author ZhuMei
 * @Date 2021/1/4 22:16
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())//new MyInterceptor()是我们自定义的拦截器类对象
                .addPathPatterns("/**")//  /**代表拦截所有请求。动态参数，多个参数以逗号隔开
                .excludePathPatterns("/my/**","/auth/captcha","/auth/login", "/auth/logout", "/webjars/**"
                        , "/js/**", "/css/**","/", "/favicon.ico", "/error");//不拦截请求。这也是个动态参数，有多个不拦截的请求时，以逗号隔开
    }
}
