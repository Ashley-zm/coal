package com.zm.coal.controller;

import com.wf.captcha.SpecCaptcha;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
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
    @Autowired
    HttpServletRequest request; //通过注解获取一个request

    /**
     * 图片流
     *
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("captcha")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpg");
        //英文与数字
        SpecCaptcha specCaptcha = new SpecCaptcha(120, 40);
        specCaptcha.setLen(4);
        String text = specCaptcha.text();
        System.out.println("生成的验证码：" + text);
        // 验证码存入session
        session.setAttribute("captcha", specCaptcha.text());
        specCaptcha.out(response.getOutputStream());
    }

    /**
     * 用户登录
     * resourceService 是登录后的左侧列表等级显示
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, String veriCode, HttpSession session,
                        RedirectAttributes attributes, Model model) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        System.out.println(captcha);
        System.out.println(veriCode);
        boolean code;
        if (captcha.equalsIgnoreCase(veriCode)) {
            code = true;
        } else {
            code = false;
        }
        LoginDTO login = accountService.login(username, password, code);
        String error = login.getError();
        System.out.println(error);
        if (error == null) {
            session.setAttribute("account", login.getAccount());
            session.setAttribute("accountId", login.getAccount().getAccountId());
            List<ResourceVO> resourceVOS = resourceService
                    .listResourceByRoleId(login.getAccount().getRoleId());
            model.addAttribute("resource", resourceVOS);

            // 将资源转换为代码模块名称的集合，权限拦截
            HashSet<String> module = resourceService.convert(resourceVOS);
            session.setAttribute("module",module);
            System.out.println("module名称"+module);
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
