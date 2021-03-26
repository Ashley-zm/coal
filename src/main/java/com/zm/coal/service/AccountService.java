package com.zm.coal.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    LoginDTO login(String username, String password,boolean code);
    /**
     * 分页查询账号
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper);

    /**
     * 根据countId查询账号信息 accountDetail
     * @param id
     * @return
     */
    Account getAccountById(Long id);

    // Account getAccountByRealName(String realName);
}
