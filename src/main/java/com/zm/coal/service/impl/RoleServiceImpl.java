package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zm.coal.entity.Role;
import com.zm.coal.entity.RoleResource;
import com.zm.coal.mapper.RoleMapper;
import com.zm.coal.mapper.RoleResourceMapper;
import com.zm.coal.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    /**
     * 新增角色及角色所具有的资源
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRole(Role role) {
        save(role);
        Long roleId = role.getRoleId();
        List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }

        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRole(Role role) {
        updateById(role);
        Long roleId = role.getRoleId();

        roleResourceMapper.delete(Wrappers.<RoleResource>lambdaQuery()
                .eq(RoleResource::getRoleId, roleId));

        List<Long> resourceIds = role.getResourceIds();
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            for (Long resourceId : resourceIds) {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleId(roleId);
                roleResource.setResourceId(resourceId);
                roleResourceMapper.insert(roleResource);
            }
        }
        return true;
    }
}
