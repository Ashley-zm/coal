package com.zm.coal.controller;

import com.zm.coal.entity.Account;
import com.zm.coal.service.AccountService;
import com.zm.coal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ZhuMei
 * @Date 2021/1/23 9:26
 * @Version 1.0
 */
@Controller
@RequestMapping("my")
public class MyController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    /**
     * 根据countId查询账号信息 accountDetail
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {
        Account account = accountService.getAccountById(id);
        model.addAttribute("account", account);
        return "my/myDetail";
    }
}
