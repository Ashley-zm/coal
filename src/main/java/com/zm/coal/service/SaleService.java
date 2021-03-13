package com.zm.coal.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zm.coal.entity.Sale;

import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/3/8 23:00
 * @Version 1.0
 */
public interface SaleService  extends IService<Sale> {
    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Sale> salePage(Page<Sale> page, Wrapper<Sale> wrapper);

    /**
     *
     * @return
     */
    List<Sale> echarsList();

    List<Sale> echarsListAll();


}
