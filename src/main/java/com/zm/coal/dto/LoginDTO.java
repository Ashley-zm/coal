package com.zm.coal.dto;

import com.zm.coal.entity.Account;
import lombok.Data;

/**
 * @Author ZhuMei
 * @Date 2021/1/4 21:52
 * @Version 1.0
 */
@Data
public class LoginDTO {
    /**
     * 登录失败或成功跳转的页面
     */
    private String path;
    /**
     * 错误提示信息
     */
    private String error;
    /**
     * 当前登录人的信息
     */
    private Account account;
}
