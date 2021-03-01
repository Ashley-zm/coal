package com.zm.coal.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author ZhuMei
 * @Date 2021/1/11 18:47
 * @Version 1.0
 */
@Data
public class TreeVO {

    /**
     * 节点标题
     */
    private String title;

    /**
     * 节点唯一索引值，用于对指定节点进行各类操作
     */
    private Long id;

    /**
     * 子节点
     */
    private List<TreeVO> children;

    /**
     * 节点是否初始为选中状态（如果开启复选框的话），默认false
     */
    private boolean checked;

}
