package com.zm.coal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author ZhuMei
 * @Date 2021/3/8 22:19
 * @Version 1.0
 */
@Data
public class Sale{
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "sale_id", type = IdType.AUTO)
    private Long saleId;

    /**
     * 合同编号
     */
    private Long contractId;

    /**
     * 车牌号
     */
    private String carCode;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机手机号
     */
    private String driverPhone;

    /**
     * 车皮重量
     */
    private double carWeigth;

    /**
     * 纳税
     * 实际纳税=(单价-单价/1.6)/1.13*13%*数量
     */
    private double taxes;

    /**
     * 利润
     * 利润=总价-单价/1.6*数量-实际纳税
     */
    private double profit;


    /**
     * 总重量
     * 煤炭总重量+车皮重量
     */
    private double totalWeigtht;

    /**
     * 出厂时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leaveTime;

    /**
     * 逻辑删除标识(0、否 1、是)
     * @TableLogic在字段上加上这个注解再执行BaseMapper的删除方法时，删除方法会变成修改
     */
    private Integer deleted;
    /**
     * 产品名称(不在sale表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String productName;

    /**
     * 产品单价(不在sale表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private double price;

    /**
     * 合同中产品的总数量(不在sale表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private double amount;

    /**
     * 合同名称(不在sale表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String contractName;


    /**
     * 合同中产品的总价格(不在sale表中，使用注解
     *
     * @TableField(exist = false)
     * 这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private double total;

}
