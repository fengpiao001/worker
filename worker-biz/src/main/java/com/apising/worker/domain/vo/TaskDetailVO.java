package com.apising.worker.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author tianxu
 */
@Getter
@Setter
@ToString
public class TaskDetailVO {
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

    /**
     * 工人姓名
     */
    private String workerName;
}
