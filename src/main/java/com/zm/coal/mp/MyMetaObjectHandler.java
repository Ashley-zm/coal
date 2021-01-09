package com.zm.coal.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.zm.coal.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

/**
 * 插入和更新时的自动填充类
 * Component spring容器管理下的bean
 * @Author ZhuMei
 * @Date 2021/1/8 21:38
 * @Version 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //判断是否有creatTime字段
        if(metaObject.hasSetter("createTime")){
            this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());
        }
        if (metaObject.hasSetter("create")){
            Object account = RequestContextHolder.getRequestAttributes()
                    .getAttribute("account", RequestAttributes.SCOPE_REQUEST);
            if (account!=null){
                Long accountId = ((Account) account).getAccountId();
                this.strictInsertFill(metaObject,"createAccountId", Long.class,accountId);
            }
        }


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifiedTime")){
            this.strictUpdateFill(metaObject,"modifiedTime",LocalDateTime.class,LocalDateTime.now());
        }
        if (metaObject.hasSetter("modifiedAccountId")){
            Object account = RequestContextHolder.getRequestAttributes()
                    .getAttribute("account", RequestAttributes.SCOPE_REQUEST);
            if (account!=null){
                Long accountId = ((Account) account).getAccountId();
                this.strictUpdateFill(metaObject,"modifiedAccountId", Long.class,accountId);
            }
        }
    }
}
