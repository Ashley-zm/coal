package com.zm.coal.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Account;
import com.zm.coal.entity.Role;
import com.zm.coal.service.AccountService;
import com.zm.coal.service.ResourceService;
import com.zm.coal.service.RoleService;
import com.zm.coal.util.ResultUtil;
import com.zm.coal.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccountService accountService;


    /**
     * 角色列表
     * @return
     */
    @GetMapping("toList")
    public String toList(){
        return "role/roleList";
    }


    /**
     * 角色查询
     * @param roleName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String,Object>> list(String roleName,Long page,Long limit){
        LambdaQueryWrapper<Role> wrapper= Wrappers.<Role>lambdaQuery()
                .like(StringUtils.isNotBlank(roleName),Role::getRoleName,roleName)
                .orderByDesc(Role::getRoleId);
        Page<Role> myPage = roleService.page(new Page<>(page, limit), wrapper);
        return ResultUtil.buildPageR(myPage);
    }

    /**
     *新增页面
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(){
        return "role/roleAdd";
    }

    /**
     * 新增角色操作
     * @param role
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {
        return ResultUtil.buildR(roleService.saveRole(role));
    }

    /**
     * 进入修改页roleUpdate页面
     * 需要接受参数id，通过getById(id)查出来待修改的客户。
     * spring对象model将获取的对象存起来。
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){
        Role role= roleService.getById(id);
        model.addAttribute("role",role);
        return "role/roleUpdate";
    }

    /**
     * 修改客户
     * @param role
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Role role){
        System.out.println("==========当前的角色id："+role.getRoleId());
        return ResultUtil.buildR(roleService.updateRole(role));
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        Integer count = accountService.lambdaQuery().eq(Account::getRoleId, id).count();
        if (count>0){
            return R.failed("有账号正拥有该角色，不能删除！");
        }
        return ResultUtil.buildR(roleService.removeById(id));
    }

    /**
     * 进入详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){
        Role role= roleService.getById(id);
        model.addAttribute("role",role);
        return "role/roleDetail";
    }

    /**
     * 角色资源的列表的新增和修改
     * @return
     */
    @GetMapping({"listResource","listResource/{roleId}","listResource/{roleId}/{flag}"})
    @ResponseBody
    public R<List<TreeVO>> listResource(@PathVariable(required = false) Long roleId
    ,@PathVariable(required = false) Integer flag){
        return R.ok(resourceService.listResource(roleId,flag));
    }
}
