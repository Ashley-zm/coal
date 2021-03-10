package com.zm.coal.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Customer;
import com.zm.coal.service.CustomerService;
import com.zm.coal.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 进入到列表页
     * @return
     */
    @GetMapping("toList")
    public String toList(){
        return "customer/customerList";
    }

    /**
     * 客户管理的查询方法
     * @param realName
     * @param phone
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String,Object>> list(String realName,String phone,Long page,Long limit){
        LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery()
                .like(StringUtils.isNotBlank(realName), Customer::getCustomerName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId);
        Page<Customer> myPage = customerService.page(new Page<>(page, limit), wrapper);
        return ResultUtil.buildPageR(myPage);
        /**
         * 方法一：条件构造器
         * 方法二：链式查询
         *         Page<Customer> myPage = customerService.lambdaQuery().like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
         *                 .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
         *                 .orderByDesc(Customer::getCustomerId).page(new Page<>(page, limit));
         *         return ResultUtil.buildPageR(myPage);
         * 方法三：条件构造器传参 添加@TableField()
         *
         */

        /**
         * 优化：新建工具类ResultUtil，抽象成公共方法buildPageR，返回分页查询返回的结果
         *         HashMap<String, Object> hashMap = new HashMap<>();
         *         System.out.println("总数量："+myPage.getTotal());
         *         hashMap.put("count",myPage.getTotal());
         *         hashMap.put("records",myPage.getRecords());
         *         System.out.println(hashMap);
         *         return R.ok(hashMap);
         */
    }

    /**
     * 进入新增页customerAdd页面
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(){
        return "customer/customerAdd";
    }

    /**
     * 新增客户
     * 由于每次的新增和删除的结果（成功、失败）都使用这个方法，
     * 抽象出来到resultUtil类中buildR方法中
     * if (save){
     *             return R.ok(null);
     *         }
     *         return R.failed("操作失败");
     * @param customer
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer){
        return ResultUtil.buildR(customerService.save(customer));
    }

    /**
     * 进入修改页customerUpdate页面
     * 需要接受参数id，通过getById(id)查出来待修改的客户。
     * spring对象model将获取的对象存起来。
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id,Model model){
        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        return "customer/customerUpdate";
    }

    /**
     * 修改客户
     * @param customer
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Customer customer){
        return ResultUtil.buildR(customerService.updateById(customer));
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        return ResultUtil.buildR(customerService.removeById(id));
    }

    /**
     * 进入详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){
        Customer customer = customerService.getById(id);
        // System.out.println(customer.getCustomerId());
        model.addAttribute("customer",customer);
        return "customer/customerDetail";
    }
}
