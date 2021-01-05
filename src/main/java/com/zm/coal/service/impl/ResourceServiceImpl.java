package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zm.coal.entity.Resource;
import com.zm.coal.mapper.ResourceMapper;
import com.zm.coal.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.vo.ResourceVO;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
    /**
     * 根据角色id，查询该角色所具有的资源
     * @param roleId
     * @return
     */
    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        /**
         * 条件构造器,第一级目录
         */
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id",roleId).isNull("re.parent_id");
        List<ResourceVO> resourceVOS=baseMapper.listResource(query);

        /**
         * 查询下级菜单
         */
        resourceVOS.forEach(r->{
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.query();
            subWrapper.eq("rr.role_id",roleId).eq("re.parent_id",resourceId);
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);
            if (CollectionUtils.isNotEmpty(subResourceVOS)){
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }

}
