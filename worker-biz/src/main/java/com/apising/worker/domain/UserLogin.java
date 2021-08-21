package com.apising.worker.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLogin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 劳务公司编码，预留字段，后续扩展平台化
     */
    private String serviceCompanyCode;

    /**
     * 用户类型 0-管理员，1-工人，2-劳务公司人员
     */
    private Integer userType;

    /**
     * 用户id，根据user_type作为区分
     */
    private Long userId;

    /**
     * 第三方登录openId
     */
    private String thirdOpenId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;


}
