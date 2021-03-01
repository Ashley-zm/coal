package com.zm.coal.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.dto.LoginDTO;
import com.zm.coal.entity.Account;
import com.zm.coal.mapper.AccountMapper;
import com.zm.coal.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public LoginDTO login(String username, String password,boolean code) {
        LoginDTO loginDTO = new LoginDTO();
        //重定向根目录
        loginDTO.setPath("redirect:/");
        //条件查询，方法引用
        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户名不存在！");
            return loginDTO;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String s = md5.digestHex(password);
        if (!s.equals(account.getPassword())) {
            loginDTO.setError("密码错误！");
            return loginDTO;
        }
        //验证码的true或者false
        while (!code){
            loginDTO.setError("验证码错误！");
            return loginDTO;
        }
        loginDTO.setAccount(account);
        //成功跳转到主页面
        loginDTO.setPath("login/main");
        return loginDTO;
    }

    /**
     * 分页查询账号
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public IPage<Account> accountPage(Page<Account> page, Wrapper<Account> wrapper) {
        return baseMapper.accountPage(page,wrapper);
    }

    @Override
    public Account getAccountById(Long id) {
        return baseMapper.selectAccountById(id);
    }
}
