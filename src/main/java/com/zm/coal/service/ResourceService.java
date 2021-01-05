package com.zm.coal.service;

import com.zm.coal.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zm.coal.vo.ResourceVO;

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
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);

}
