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
public class TaskDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 任务状态 0-草稿，10-招聘中，20-进行中，30-已完成，40-已终止
     */
    private Integer taskStatus;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 工人id
     */
    private Long workerId;

    /**
     * 实际开始时间
     */
    private Date startTime;

    /**
     * 实际结束时间
     */
    private Date endTime;


}
