package com.zm.coal.service.impl;

import cn.hutool.core.lang.intern.InternUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.entity.Contract;
import com.zm.coal.entity.Customer;
import com.zm.coal.entity.Product;
import com.zm.coal.mapper.ContractMapper;
import com.zm.coal.mapper.CustomerMapper;
import com.zm.coal.mapper.ProductMapper;
import com.zm.coal.service.ContractService;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 新增合同及合同所具有的产品、销售员、客户
     * @param contract
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveContract(Contract contract) {
        save(contract);
        Long contractId = contract.getContractId();
        Long accountId = contract.getAccountId();
        Long customerId = contract.getCustomerId();
        Long productId = contract.getProductId();
        if (contractId!=null){
            contract.setAccountId(accountId);
            contract.setCustomerId(customerId);
            contract.setProductId(productId);
            contractMapper.insert(contract);
        }
        return true;
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
