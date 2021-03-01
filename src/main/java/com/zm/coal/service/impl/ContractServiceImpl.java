package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.entity.Contract;
import com.zm.coal.mapper.ContractMapper;
import com.zm.coal.service.ContractService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 合同表 服务实现类
 * </p>
 *
 * @Author ZhuMei
 * @Date 2021/2/21 14:11
 * @Version 1.0
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements ContractService {

    /**
     * 分页查询合同
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public IPage<Contract> contractPage(Page<Contract> page, Wrapper<Contract> wrapper) {
        return baseMapper.contractPage(page, wrapper);
    }
}
