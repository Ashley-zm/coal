package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.entity.Sale;
import com.zm.coal.mapper.SaleMapper;
import com.zm.coal.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/3/8 23:01
 * @Version 1.0
 */
@Service
public class SaleServiceImpl extends ServiceImpl<SaleMapper, Sale> implements SaleService {


    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public IPage<Sale> salePage(Page<Sale> page, Wrapper<Sale> wrapper) {
        return baseMapper.salePage(page, wrapper);
    }

    /**
     * 产品的总销售量、总销售额、
     * @return
     */
    @Override
    public List<Sale> echarsListAll() {
        return baseMapper.echarsListSelectAll();
    }

    /**
     * 日纳税-利润
     * @return
     */
    @Override
    public List<Sale> echarsList() {
        return baseMapper.echarsListSelect();
    }

    /**
     * 产品各种类的月销售量
     * @return
     */
    @Override
    public List<Sale> echarsListMonth() {
        return baseMapper.echarsListSelectMonth();
    }

    /**
     * 产品各种类的年销售量
     * @return
     */
    @Override
    public List<Sale> echarsListYear() {
        return baseMapper.echarsListSelectYear();
    }
}
