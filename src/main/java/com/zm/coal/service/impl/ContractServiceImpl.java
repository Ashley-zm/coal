package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.entity.Contract;
import com.zm.coal.mapper.ContractMapper;
import com.zm.coal.mapper.CustomerMapper;
import com.zm.coal.mapper.ProductMapper;
import com.zm.coal.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     *
     */
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ContractMapper contractMapper;

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

    @Override
    public Contract getContractById(Long id) {
        /**
         * 对应mapper中的接口的方法selectContractById
         */
        return baseMapper.selectContractById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateContract(Contract contract) {
        // updateById(contract);
        // Long contractId = contract.getContractId();
        // contractMapper.delete(Wrappers.<Contract>lambdaQuery().eq(Contract::getProductId,));
        return false;
    }
}
