package com.zm.coal.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Account;
import com.zm.coal.entity.Contract;
import com.zm.coal.entity.Customer;
import com.zm.coal.query.ContractQuery;
import com.zm.coal.service.ContractService;
import com.zm.coal.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 合同表 前端控制器
 * </p>
 *
 * @Author ZhuMei
 * @Date 2021/2/21 14:11
 * @Version 1.0
 */
@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    /**
     * 跳转到账户列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "contract/contractList";
    }

    /**
     * 合同管理的查询方法
     * 查询条件封装成ContractQuery对象 query/ContractQuery
     *
     * @param query
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(ContractQuery query) {
        QueryWrapper<Contract> wrapper = Wrappers.<Contract>query()
                .like(StringUtils.isNotBlank(query.getContractCode()), "c.contract_id", query.getContractCode())
                .like(StringUtils.isNotBlank(query.getCustomerName()), "u.customer_name", query.getCustomerName())
                .like(StringUtils.isNotBlank(query.getRealName()), "a.real_name", query.getRealName())
                .eq("c.deleted", 0);
        String createTimeRange = query.getCreateTimeRange();
        String status = query.getStatus();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(status);
        /**
         * 根据status的值，来筛选合同的状态
         * ge大于等于 >= ; le <=
         */
        if (StringUtils.isNotBlank(status)) {
            if (status.equals("0")) {
                wrapper.ge("c.effective_time", df.format(new Date()));
                System.out.println("效力待定");
            } else if (status.equals("1")) {
                wrapper.le("c.effective_time", df.format(new Date()))
                        .ge("c.expire_time", df.format(new Date()));
                System.out.println("生效中");
            } else {
                wrapper.le("c.expire_time", df.format(new Date()));
                System.out.println("已无效");
            }
        }
        if (StringUtils.isNotBlank(createTimeRange)) {
            String[] timeArray = createTimeRange.split(" - ");
            wrapper.ge("c.create_time", timeArray[0])
                    .le("c.create_time", timeArray[1]);
        }
        IPage<Contract> contractIPage = contractService.contractPage(new Page<>(query.getPage(), query.getLimit()), wrapper);
        return ResultUtil.buildPageR(contractIPage);
    }

}