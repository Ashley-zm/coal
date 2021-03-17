package com.zm.coal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Sale;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/3/8 23:02
 * @Version 1.0
 */
public interface SaleMapper extends BaseMapper<Sale> {
    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Sale> salePage(Page<Sale> page, @Param(Constants.WRAPPER) Wrapper<Sale> wrapper);

    /**
     * 产品的总销售量、总销售额、
     * @return
     */
    List<Sale> echarsListSelectAll();

    /**
     * 日纳税-利润
     * @return
     */
    List<Sale> echarsListSelect();

    /**
     * 产品各种类的月销售量
     * @return
     */
    List<Sale> echarsListSelectMonth();

    /**
     * 产品各种类的年销售量
     * @return
     */
    List<Sale> echarsListSelectYear();

    /**
     * 根据 id 查询合同相关的信息
     * @param id
     * @return
     */
    Sale selectSaleById(Long id);
}
