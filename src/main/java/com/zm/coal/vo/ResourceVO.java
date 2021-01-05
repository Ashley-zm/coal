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
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 左侧列表的下级资源
     */
    private List<ResourceVO> subs;
}
