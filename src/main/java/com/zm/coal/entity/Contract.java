package com.zm.coal.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class Contract extends BaseEntity {
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
}
