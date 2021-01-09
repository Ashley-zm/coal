package com.zm.coal.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 账号表
 * </p>
 *
 * @author ZhuMei
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "account_id", type = IdType.AUTO)
    private Long accountId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称(不在account表中，使用注解
     *     @TableField(exist = false)
     *     这样就不会自动映射了。)
     */
    @TableField(exist = false)
    private String roleName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

}
