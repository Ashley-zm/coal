package com.zm.coal.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/1/5 20:28
 * @Version 1.0
 */
@Data
public class ResourceVO {
    /**
     * 主键
     */
    private Long resourceId;
    private Long parentId;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 资源类型(0、目录 1、菜单 2、按钮)
     */
    private Integer resourceType;
    /**
     * 请求地址
     */
    private String url;

    /**
     * code
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 左侧列表的下级资源
     */
    private List<ResourceVO> subs;
}
