package com.apising.worker.domain.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class TaskVO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 修改人id
     */
    private Long modifierId;

    /**
     * 是否删除  0-未删除，其他-删除
     */
    private Integer deleted;


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
