package com.zm.coal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @Author ZhuMei
 * @Date 2021/03/02 2:13
 * @Version 1.0
 */

@Data
public class Product {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Long productId;


    /**
     * 产品名称
     */
    private String productName;

    /**
     * 总数量
     */
    private double pTotal;

    /**
     * 单价
     */
    private double price;


    /**
     * 逻辑删除标识(0、否 1、是)
     */
    @TableLogic
    private Integer deleted;
}
