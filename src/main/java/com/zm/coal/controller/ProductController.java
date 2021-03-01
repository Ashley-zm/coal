package com.zm.coal.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Product;
import com.zm.coal.service.ProductService;
import com.zm.coal.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author ZhuMei
 * @Date 2021/03/02 2:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 商品列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "product/productList";
    }


    /**
     * 产品管理 查询
     * @param productName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String productName, Long page, Long limit) {
        LambdaQueryWrapper<Product> wrapper = Wrappers.<Product>lambdaQuery()
                .like(StringUtils.isNotBlank(productName), Product::getProductName, productName)
                .orderByDesc(Product::getProductId);
        Page<Product> myPage = productService.page(new Page<>(page, limit), wrapper);
        return ResultUtil.buildPageR(myPage);
    }
}