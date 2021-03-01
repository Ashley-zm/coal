package com.zm.coal.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;

import java.util.List;

/**
 * 自定义Sql注入器
 * @Author ZhuMei
 * @Date 2021/1/13 20:46
 * @Version 1.0
 */
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 增加根据id逻辑删除数据，并带字段填充功能
        methodList.add(new LogicDeleteByIdWithFill());
        return methodList;
    }
}
