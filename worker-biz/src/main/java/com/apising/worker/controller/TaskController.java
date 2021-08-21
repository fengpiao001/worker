package com.apising.worker.controller;


import com.apising.common.lang.domain.Result;
import com.apising.worker.domain.Task;
import com.apising.worker.domain.vo.TaskDetailVO;
import com.apising.worker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping("/createTask")
    public Result<Task> createTask(@RequestBody Task task){
        return Result.success(taskService.createTask(task));
    }

    @RequestMapping("/applyTask")
    public Result<String> applyTask(Long taskId){
        taskService.applyTask(taskId);
        return Result.success(null);
    }


    @RequestMapping("/taskDetailsByTaskId")
    public Result<List<TaskDetailVO>> taskDetailsByTaskId(Long taskId){
        return Result.success(taskService.getTaskDetailByTaskId(taskId));
    }



}
