package com.apising.worker.service.impl;

import com.apising.common.lang.convertor.BaseConvertor;
import com.apising.common.lang.domain.Page;
import com.apising.common.lang.exception.XException;
import com.apising.common.lang.session.SessionLocal;
import com.apising.common.lang.util.ListUtil;
import com.apising.worker.domain.Task;
import com.apising.worker.domain.TaskDetail;
import com.apising.worker.domain.Worker;
import com.apising.worker.domain.enums.TaskStatus;
import com.apising.worker.domain.vo.TaskDetailVO;
import com.apising.worker.domain.vo.TaskQuery;
import com.apising.worker.mapper.TaskDetailMapper;
import com.apising.worker.mapper.TaskMapper;
import com.apising.worker.mapper.WorkerMapper;
import com.apising.worker.service.TaskService;
import com.apising.worker.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    TaskDetailMapper taskDetailMapper;
    @Autowired
    WorkerMapper workerMapper;

    /**
     * 创建任务
     *
     * @param task
     * @return
     */
    @Override
    public Task createTask(Task task) {
        if(TaskStatus.publishing.equal(task.getTaskStatus())){
            checkParam(task);
        }
        if(task.getId() == null){
            taskMapper.insert(task);
        }else{
            taskMapper.updateById(task);
        }
        return task;
    }

    /**
     * 校验参数
     * @param task
     * @return
     */
    private void checkParam(Task task){
        Assert.notNull(task.getTaskStatus(),"任务状态不能为空");
        Assert.notNull(task.getCheckoutType(),"结算类型不能为空");
        Assert.state(StringUtils.isNotEmpty(task.getTitle()),"任务标题不能为空");
        Assert.state(StringUtils.isNotEmpty(task.getJobDetail()),"工作描述不能为空");
        Assert.notNull(task.getNeedWorkerNum(),"需要人数不能为空");
        Assert.notNull(task.getHourPrice(),"小时单价不能为空");
    }


    @Override
    public void applyTask(Long taskId) {
        Assert.notNull(taskId,"任务id不能为空");
        Task task = taskMapper.selectById(taskId);
        Assert.notNull(task,"任务不存在");
        Integer needWorkerNum = task.getNeedWorkerNum();
        List<TaskDetail> detailList = getTaskDetailListByTaskId(taskId);
        if(detailList.size() >= needWorkerNum){
            throw new XException().setShowText("当前任务服务人数已满");
        }
        Long workerId = SessionLocal.getRequireWorkerId();
        detailList.forEach(e ->{
            if(workerId.equals(detailList)){
                throw new XException().setShowText("您已申请了该任务");
            }
        });

        TaskDetail detail = new TaskDetail();
        detail.setTaskId(taskId);
        detail.setTaskStatus(TaskStatus.publishing.getIndex());
        detail.setWorkerId(workerId);
        detail.setCreatorId(workerId);
        taskDetailMapper.insert(detail);
    }

    /**
     * 根据任务id获取任务明细
     *
     * @param taskId
     * @return
     */
    @Override
    public List<TaskDetailVO> getTaskDetailByTaskId(Long taskId) {
        List<TaskDetail> detailList = getTaskDetailListByTaskId(taskId);
        List<TaskDetailVO> voList = BaseConvertor.changeList(detailList,TaskDetailVO.class);
        List<Long> workerIds = voList.stream().map(e->e.getWorkerId()).collect(Collectors.toList());
        List<Worker> workers = workerMapper.selectBatchIds(workerIds);
        Map<Long,Worker> workerMap = workers.stream().collect(Collectors.toMap(e->e.getId(),e->e));
        for(TaskDetailVO item : voList){
            Worker worker = workerMap.get(item.getWorkerId());
            if(worker != null){
                item.setWorkerName(worker.getRealName());
            }
        }
        return voList;
    }

    /**
     * 根据任务id获取申请任务明细,并过滤掉作废的数据
     * @param taskId
     * @return
     */
    public List<TaskDetail> getTaskDetailListByTaskId(Long taskId){
        QueryWrapper<TaskDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("task_id",taskId);
        List<TaskDetail> list = taskDetailMapper.selectList(queryWrapper);
        return list.stream().filter(e->!TaskStatus.stoped.equal(e.getTaskStatus())).collect(Collectors.toList());
    }

    @Override
    public Page<List<Task>> list(TaskQuery query) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();

        IPage<Task> iPage = taskMapper.selectPage(PageUtil.pageToMybatisPage(query), queryWrapper);

        return PageUtil.mybatisPageToPage(iPage);
    }
}
