package com.zm.coal.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ZhuMei
 * @Date 2021/1/4 20:23
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor实现了mybatis的拦截器接口
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        addInnerInterceptor分页查询的拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 自定义SQL注入器
     * @return
     */
    @Bean
    public DefaultSqlInjector sqlInjector(){
        return new MySqlInjector();
    }


}
