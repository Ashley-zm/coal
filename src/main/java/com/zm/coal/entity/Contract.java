package com.zm.coal.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * <p>
 * 合同表
 * </p>
 *
 * @Author ZhuMei
 * @Date 2021/2/21 13:42
 * @Version 1.0
 * EqualsAndHashCode
 * 用于子类 继承父类时自动的给model bean实现equals方法和hashcode方法。
 */

@Data
// @EqualsAndHashCode(callSuper = true)
public class Contract {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "contract_id", type = IdType.AUTO)
    private Long contractId;

    /**
     * 合同编号
     */
    private String contractCode;

    /**
     * 客户编号
     */
    private Long customerId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 销售人ID
     */
    private Long accountId;
    /**
     * 销售人ID
     */
    private Long productId;
    /**
     * 销售人名称(不在contract表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String realName;

    /**
     * 客户姓名(不在contract表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String customerName;

    /**
     * 产品名称(不在contract表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品单价(不在contract表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private double price;

    /**
     * 数量
     */
    private double amount;

    /**
     * 总价
     */
    private double total;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 生效时间
     * 问题：Cannot deserialize value of type `java.time.LocalDateTime` from String
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime effectiveTime;

    /**
     * 到期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;

    /**
     * 是否出厂状态标识(0：否 ，1：是)
     */
    private Integer factoryState;
    /**
     * 合同状态
     */
    @TableField(exist = false)
    private String status;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifiedTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createAccountId;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long modifiedAccountId;

    /**
     * 逻辑删除标识(0、否 1、是)
     * @TableLogic在字段上加上这个注解再执行BaseMapper的删除方法时，删除方法会变成修改
     */
    @TableLogic
    private Integer deleted;
}
