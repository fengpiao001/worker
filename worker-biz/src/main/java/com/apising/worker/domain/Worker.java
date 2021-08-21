package com.apising.worker.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.apising.worker.domain.BaseEntity;
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
public class Worker extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号码
     */
    private String identifyCode;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别 1-男，2-女，0-未知
     */
    private Integer sex;

    /**
     * 银行卡号
     */
    private String bankNo;

    /**
     * 银行名称，包含支行
     */
    private String bankName;

    /**
     * 身体健康程度 0-健康 1-亚健康 2-轻微疾病 3-大伤
     */
    private Integer healthLevel;


}
