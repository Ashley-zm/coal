package com.zm.coal.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

/**
 *1. 权限拦截器
 *自定义拦截器（该类必须实现HandlerInterceptor接口）
 * @Author ZhuMei
 * @Date 2021/1/13 20:50
 * @Version 1.0
 */
public class MyInterceptor implements HandlerInterceptor {
    //拦截器开始时执行的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器开始执行");
        // 获取URL
        String requestURI = request.getRequestURI();
        System.out.println("requestURI::"+requestURI);
        // /account/toList account/toList
        String substring = requestURI.substring(1);
        System.out.println("substring::"+substring);
        // /account/toList account
        int index = substring.indexOf("/");
        if (index != -1) {
            // account
            substring = substring.substring(0, index);
        }
        System.out.println("substring的值："+substring);

        //问题：urls 的值为null。session中module没有值
        HashSet<String> urls = (HashSet<String>) request.getSession().getAttribute("module");
        System.out.println("urls的值:"+urls);

        // account是否在 该用户所有的资源中
        System.out.println(urls + "=======" + substring);
        boolean result = urls.stream().anyMatch(substring::equals);
        System.out.println("account是否在 该用户所有的资源中:" + result);
        if (!result) {
            response.sendRedirect("/");
        }
        return result;
    }
}
