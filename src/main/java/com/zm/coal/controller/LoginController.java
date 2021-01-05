package com.zm.coal.controller;

import com.zm.coal.dto.LoginDTO;
import com.zm.coal.service.AccountService;
import com.zm.coal.service.ResourceService;
import com.zm.coal.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/1/4 21:42
 * @Version 1.0
 */
@Controller
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 用户登录
     * resourceService 是登录后的左侧列表等级显示
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, HttpSession session,
                        RedirectAttributes attributes, Model model) {
        LoginDTO login = accountService.login(username, password);
        String error = login.getError();
        if (error == null) {
            session.setAttribute("account", login.getAccount());
            List<ResourceVO> resourceVOS = resourceService.listResourceByRoleId(login.getAccount().getRoleId());
            model.addAttribute("resource", resourceVOS);
        } else {
            attributes.addFlashAttribute("error", error);
        }
        return login.getPath();
    }

    /**
     * 用户退出登录
     *
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
