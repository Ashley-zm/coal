package com.zm.coal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zm.coal.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zm.coal.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 分页查询账号
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page,@Param(Constants.WRAPPER) Wrapper<Account> wrapper);


    /**
     * 根据countId查询账号信息 accountDetail
     * @param id
     * @return
     */
    Account selectAccountById(Long id);

    // Account selectAccountByRealName(String realName);
}
