package com.zm.coal.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zm.coal.entity.Account;
import com.zm.coal.entity.Contract;
import org.apache.ibatis.annotations.Param;

/**
 *
 * <p>
 * 合同表表 服务类
 * </p>
 * @Author ZhuMei
 * @Date 2021/2/21 14:10
 * @Version 1.0
 */
public interface ContractService extends IService<Contract> {

    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Contract> contractPage(Page<Contract> page, Wrapper<Contract> wrapper);

    /**
     * 根据contractId查询账号信息contractDetail
     * @param id
     * @return
     */
    Contract getContractById(Long id);


}
