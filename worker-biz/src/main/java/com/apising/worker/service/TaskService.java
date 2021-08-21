package com.apising.worker.service;

import com.apising.worker.domain.Task;
import com.apising.worker.domain.TaskDetail;
import com.apising.worker.domain.vo.TaskDetailVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
public interface TaskService extends IService<Task> {

    /**
     * 创建任务
     * @param task
     * @return
     */
    public Task createTask(Task task);

    /**
     * 申请任务
     * @param taskId
     */
    public void applyTask(Long taskId);

    /**
     * 根据任务id获取任务明细
     * @param taskId
     * @return
     */
    public List<TaskDetailVO> getTaskDetailByTaskId(Long taskId);

}