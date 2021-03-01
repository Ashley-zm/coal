package com.zm.coal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zm.coal.entity.Resource;
import com.zm.coal.mapper.ResourceMapper;
import com.zm.coal.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zm.coal.vo.ResourceVO;
import com.zm.coal.vo.TreeVO;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
     *
     * @param roleId
     * @return
     */
    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        /**
         * 条件构造器,第一级目录
         */
        QueryWrapper<Resource> query = Wrappers.query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = baseMapper.listResource(query);

        /**
         * 查询下级菜单
         */
        resourceVOS.forEach(r -> {
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.query();
            subWrapper.eq("rr.role_id", roleId)
                    .eq("re.parent_id", resourceId).orderByAsc("re.sort");
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);
            if (CollectionUtils.isNotEmpty(subResourceVOS)) {
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }


    /**
     * 查询系统资源，前端组件渲染
     * 在增加角色中渲染
     * 在更新角色中渲染（此处需要判断本身的角色id）
     *
     * @return
     */
    @Override
    public List<TreeVO> listResource(Long roleId, Integer flag) {
        if (roleId == null) {
            LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                    .isNull(Resource::getParentId).orderByAsc(Resource::getSort);
            List<Resource> resources = list(wrapper);

            List<TreeVO> treeVOS = resources.stream().map(r -> {
                TreeVO treeVO = new TreeVO();
                treeVO.setId(r.getResourceId());
                treeVO.setTitle(r.getResourceName());

                LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                        .eq(Resource::getParentId, r.getResourceId()).orderByAsc(Resource::getSort);
                List<Resource> subResources = list(subWrapper);
                if (CollectionUtils.isNotEmpty(subResources)) {
                    List<TreeVO> children = subResources.stream().map(sub -> {
                        TreeVO subTreeVO = new TreeVO();
                        subTreeVO.setId(sub.getResourceId());
                        subTreeVO.setTitle(sub.getResourceName());
                        return subTreeVO;
                    }).collect(Collectors.toList());
                    treeVO.setChildren(children);
                }
                return treeVO;
            }).collect(Collectors.toList());
            return treeVOS;
        } else {
            QueryWrapper<Resource> query = Wrappers.<Resource>query();
            query.eq(flag == 1, "rr.role_id", roleId)
                    .isNull("re.parent_id")
                    .orderByAsc("re.sort");
            List<TreeVO> treeVOS = baseMapper.listResourceByRoleId(query, roleId);
            treeVOS.forEach(t -> {
                t.setChecked(false);
                Long id = t.getId();
                QueryWrapper<Resource> subWrapper = Wrappers.<Resource>query();
                subWrapper
                        .eq(flag == 1, "rr.role_id", roleId)
                        .eq("re.parent_id", id)
                        .orderByAsc("re.sort");

                List<TreeVO> children = baseMapper.listResourceByRoleId(subWrapper, roleId);
                if (CollectionUtils.isNotEmpty(children)) {
                    t.setChildren(children);
                }
            });
            return treeVOS;
        }
    }

    /**
     * 权限拦截：把资源进行遍历，获取角色所对应的资源路径，
     * 进行截取，返回module.add(截取好的所有路径)，存在了HashSet中
     * @param resourceVOS
     * @return
     *
     * 问题：遍历资源
     */
    @Override
    public HashSet<String> convert(List<ResourceVO> resourceVOS) {
        HashSet<String> module = new HashSet<>();
        resourceVOS.forEach(r -> {
            String url = r.getUrl();
            if (StringUtils.isNotBlank(url)) {
                module.add(url.substring(0, url.indexOf("/")));
            }
            List<ResourceVO> subResourceVOs = r.getSubs();
            if (CollectionUtils.isNotEmpty(subResourceVOs)) {
                subResourceVOs.forEach(sub -> {
                    String subUrl = sub.getUrl();
                    if (StringUtils.isNotBlank(subUrl)) {
                        module.add(subUrl.substring(0, subUrl.indexOf("/")));
                    }
                });
            }
        });
        return module;
    }


}
