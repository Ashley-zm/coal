package com.zm.coal.service.impl;

import cn.hutool.crypto.digest.MD5;
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
    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        //重定向根目录
        loginDTO.setPath("redirect:/");
        //链式
        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户名不存在！");
            return loginDTO;
        }
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String s = md5.digestHex(password);
        if (!s.equals(account.getPassword())){
            loginDTO.setError("密码错误！");
            return loginDTO;
        }
        loginDTO.setAccount(account);
        //成功跳转到主页面
        loginDTO.setPath("login/main");
        return loginDTO;
    }
}
