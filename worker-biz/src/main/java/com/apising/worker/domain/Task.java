package com.apising.worker.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
public class Task extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 工作内容描述
     */
    private String jobDetail;

    /**
     * 结算类型 1-月结固定日期，2-固定工期结算
     */
    private Integer checkoutType;

    /**
     * 结算类型为月结固定日期时，月结算日期,如 1号
     */
    private Integer monthCheckout;

    /**
     * 固定工期结算时，工期开始时间
     */
    private Date workTimeStart;

    /**
     * 固定工期结算时，工期结束时间
     */
    private Date workTimeEnd;

    /**
     * 工作地省id
     */
    private Long provinceId;

    /**
     * 工作地城市id
     */
    private Long cityId;

    /**
     * 需要工人人数
     */
    private Integer needWorkerNum;

    /**
     * 小时单价
     */
    private BigDecimal hourPrice;

    /**
     * 任务状态 0-草稿，10-招聘中，20-进行中，30-已完成，40-已终止
     */
    private Integer taskStatus;


}
