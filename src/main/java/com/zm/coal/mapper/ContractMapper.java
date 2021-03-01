package com.zm.coal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Contract;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ZhuMei
 * @Date 2021/2/21 14:13
 * @Version 1.0
 */
public interface ContractMapper  extends BaseMapper<Contract>  {
    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Contract> contractPage(Page<Contract> page, @Param(Constants.WRAPPER) Wrapper<Contract> wrapper);


}
