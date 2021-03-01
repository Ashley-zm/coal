package com.zm.coal.service;

import com.zm.coal.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zm.coal.vo.ResourceVO;
import com.zm.coal.vo.TreeVO;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
public interface ResourceService extends IService<Resource> {
    /**
     * 根据角色id，查询该角色所具有的资源
     *
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

    /**
     * 查询系统资源，前端组件渲染
     *
     * @return
     */
    List<TreeVO> listResource(Long roleId, Integer flag);


    /**
     * 资源权限拦截，资源路径
     * @param resourceVOS
     * @return
     */
    HashSet<String> convert(List<ResourceVO> resourceVOS);
}
