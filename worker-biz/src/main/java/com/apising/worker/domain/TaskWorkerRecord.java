package com.apising.worker.domain;

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
public class TaskWorkerRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 工人id
     */
    private Long workerId;

    /**
     * 工作日期
     */
    private Date workDate;

    /**
     * 工作小时数
     */
    private Integer workHour;

    /**
     * 当天工作开始时间
     */
    private Date workStart;

    /**
     * 当天工作结束时间
     */
    private Date workEnd;

    /**
     * 备注
     */
    private String mark;


}
