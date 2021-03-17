package com.zm.coal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zm.coal.entity.Contract;
import com.zm.coal.entity.Product;
import com.zm.coal.entity.Sale;
import com.zm.coal.query.SaleQuery;
import com.zm.coal.service.ContractService;
import com.zm.coal.service.ProductService;
import com.zm.coal.service.SaleService;
import com.zm.coal.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author ZhuMei
 * @Date 2021/3/8 22:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ProductService productService;

    /**
     * 跳转到出厂销售列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "sale/saleList";
    }


    /**
     * 出厂订单的查询方法
     * 将查询字段封装到了 query/Salequery 中
     * 通过此来查询并渲染表格
     *
     * @param saleQuery
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(SaleQuery saleQuery) {
        QueryWrapper<Sale> wapper = Wrappers.<Sale>query()
                .like(StringUtils.isNotBlank(saleQuery.getContractName()), "c.contract_name", saleQuery.getContractName())
                .like(StringUtils.isNotBlank(saleQuery.getProductName()), "p.product_name", saleQuery.getProductName())
                .like(StringUtils.isNotBlank(saleQuery.getDriverName()), "s.driver_name", saleQuery.getDriverName())
                .orderByDesc(saleQuery.getSaleId())
                .eq("s.deleted", 0);
        IPage<Sale> saleIPage = saleService.salePage(new Page<>(saleQuery.getPage(), saleQuery.getLimit()), wapper);
        return ResultUtil.buildPageR(saleIPage);
    }

    /**
     * 打开出厂表单
     *
     * @param model
     * @return
     */
    @GetMapping("toAdd/{id}")
    public String toAdd(@PathVariable Long id, Model model) {
        Contract contracts = contractService.getContractById(id);
        model.addAttribute("contracts", contracts);
        return "sale/saleAdd";
    }

    /**
     * 新增出厂信息
     * @param sale
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Sale sale) {
        Long contractId = sale.getContractId();
        Contract contract = contractService.getById(contractId);
        Long productId = contract.getProductId();
        double amount = contract.getAmount();

        Product product = productService.getById(productId);
        double pTotal = product.getPTotal();

        double totalUpdate=pTotal-amount;
        product.setPTotal(totalUpdate);
        contract.setFactoryState(1);
        ResultUtil.buildR(productService.updateById(product));
        ResultUtil.buildR(contractService.updateById(contract));

        return ResultUtil.buildR(saleService.save(sale));
    }

    /**
     * 产品的总销售量、总销售额、日纳税-利润
     * @param model
     * @return
     */
    @RequestMapping("/saleAll")
    public String myECharts(Model model){
        List<Sale> echarsListAll = saleService.echarsListAll();
        model.addAttribute("echarsListAll",echarsListAll);
        return "sale/saleAll";
    }

    /**
     * 进入详情页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){
        Sale sale = saleService.getSaleById(id);
        model.addAttribute("sale",sale);
        return  "sale/saleDetail";
    }

    /**
     * 删除出厂单
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id){
        return  ResultUtil.buildR(saleService.removeById(id));
    }

    /**
     * 总销售额
     * @param model
     * @return
     */
    @RequestMapping("/saleMoney")
    public String myEChartsMoney(Model model){
        List<Sale> echarsListAll = saleService.echarsListAll();
        model.addAttribute("echarsListAll",echarsListAll);
        return "sale/saleMoney";
    }

    /**
     * 日纳税-利润
     * @param model
     * @return
     */
    @RequestMapping("/saleDay")
    public String myEChartsDay(Model model){
        List<Sale> echarsListDay = saleService.echarsList();
        model.addAttribute("echarsListDay",echarsListDay);
        return "sale/saleDay";
    }

    /**
     * 产品各种类的月销售量
     * @param model
     * @return
     */
    @RequestMapping("/saleMonth")
    public String myEChartsMonth(Model model){
        List<Sale> echarsListMonth = saleService.echarsListMonth();
        model.addAttribute("echarsListMonth",echarsListMonth);
        return "sale/saleMonth";
    }

    /**
     * 产品各种类的月销售量
     * @param model
     * @return
     */
    @RequestMapping("/saleYear")
    public String myEChartsYear(Model model){
        List<Sale> echarsListYear = saleService.echarsListYear();
        model.addAttribute("echarsListYear",echarsListYear);
        return "sale/saleYear";
    }

}
