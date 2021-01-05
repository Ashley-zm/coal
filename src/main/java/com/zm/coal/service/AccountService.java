package com.zm.coal.service;

import com.zm.coal.dto.LoginDTO;
import com.zm.coal.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
public interface AccountService extends IService<Account> {
    LoginDTO login(String username, String password);
}
