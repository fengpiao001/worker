package com.apising.worker.service;

import com.apising.common.lang.domain.Page;
import com.apising.worker.domain.Task;
import com.apising.worker.domain.TaskDetail;
import com.apising.worker.domain.vo.TaskDetailVO;
import com.apising.worker.domain.vo.TaskQuery;
import com.apising.worker.domain.vo.TaskVO;
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

    /**
     * 按条件查询任务
     * @param taskQuery
     * @return
     */
    public Page<List<Task>> list(TaskQuery taskQuery);

    /**
     * 根据id查询任务详情
     * @param id
     * @return
     */
    public TaskVO detail(Long id);

}
