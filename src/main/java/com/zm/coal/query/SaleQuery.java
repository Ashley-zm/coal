package com.zm.coal.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 查询条件封装成SaleQuery对象
 * @Author ZhuMei
 * @Date 2021/3/9 8:37
 * @Version 1.0
 */
@Data
public class SaleQuery {
    /**
     * 出厂id
     */
    private String saleId;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 司机姓名
     */
    private String driverName;

    private Long page;
    private Long limit;

}
