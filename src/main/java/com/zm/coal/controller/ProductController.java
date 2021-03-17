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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                .orderByAsc(Product::getProductId);
        Page<Product> myPage = productService.page(new Page<>(page, limit), wrapper);
        return ResultUtil.buildPageR(myPage);
    }

    /**
     * 进入新增页 productAdd 页面
     * 进厂时需要选择产品进行加量
     *
     * @param model
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd(Model model){
        List<Product> products = productService.list(Wrappers.<Product>lambdaQuery().orderByAsc(Product::getProductId));
        model.addAttribute("products",products);
        return "product/productAdd";
    }

    /**
     * 新增产品数量
     * 根据id，通过 getProductById（Mapper）方法来获取数据库中product表中的数据
     * 更新产品数量，并且将原有的price在更新下
     * @param product
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Product product){
        double pTotal = productService.getProductById(product.getProductId()).getPTotal();
        double price = productService.getProductById(product.getProductId()).getPrice();
        product.setPTotal(pTotal+product.getPTotal());
        product.setPrice(price);
        return ResultUtil.buildR(productService.updateById(product));
    }

    /**
     * 修改产品页面，通过id查询来渲染产品更新页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Long id ,Model model){
        Product product = productService.getById(id);
        model.addAttribute("product",product);
        return "product/productUpdate";
    }

    /**
     * 修改产品的信息，修改产品的名称和单价，总数量也可以修改
     * @param product
     * @return
     */
    @PutMapping
    @ResponseBody
    public R<Object> update(@RequestBody Product product){
        return ResultUtil.buildR(productService.updateById(product));
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        return ResultUtil.buildR(productService.removeById(id));
    }

    /**
     * 进入详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){
        Product product = productService.getProductById(id);
        model.addAttribute("product",product);
        return  "product/productDetail";
    }


}