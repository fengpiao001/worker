package com.apising.worker.domain.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录请求参数
 * @author
 */
@Getter
@Setter
@ToString
public class LoginReq {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录类型  0-管理员，1-工人，2-员工
     */
    private Integer userType;
}
