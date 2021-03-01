package com.zm.coal.query;

import lombok.Data;

/**
 * 查询条件封装成AccountQuery对象
 * @Author ZhuMei
 * @Date 2021/1/9 14:42
 * @Version 1.0
 */
@Data
public class AccountQuery {

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 时间范围（accountList.html 创建时间)
     */
    private String createTimeRange;

    private Long page;
    private Long limit;
}
