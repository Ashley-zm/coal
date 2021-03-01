package com.zm.coal.query;

import lombok.Data;

/**
 * 查询条件封装成ContractQuery对象
 * @Author ZhuMei
 * @Date 2021/03/01 21:32
 * @Version 1.0
 */
@Data
public class ContractQuery {
    /**
     * 合同编号
     */
    private String contractCode;
    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 销售人
     */
    private String realName;

    /**
     * 合同状态
     */
    private String status;

    /**
     * 生效时间（constractList.html 生效时间)
     */
    private String effectiveTime;

    /**
     * 创建时间范围
     */
    private String createTimeRange;

    /**
     * 到期时间
     */
    private String expireTime;

    private Long page;
    private Long limit;
}
