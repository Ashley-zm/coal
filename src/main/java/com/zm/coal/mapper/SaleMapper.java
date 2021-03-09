package com.zm.coal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Sale;
import org.apache.ibatis.annotations.Param;

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

}
