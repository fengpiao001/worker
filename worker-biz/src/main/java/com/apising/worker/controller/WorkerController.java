package com.apising.worker.controller;


import com.apising.common.lang.domain.Page;
import com.apising.common.lang.domain.Result;
import com.apising.worker.domain.Worker;
import com.apising.worker.domain.vo.WorkerQuery;
import com.apising.worker.service.WorkerService;
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
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @RequestMapping("/query")
    public Result<Page<List<Worker>>> query(WorkerQuery query){
        Page<List<Worker>> page = workerService.query(query);
        return Result.success(page);
    }

    @RequestMapping("/insert")
    public Result<Integer> insert(@RequestBody Worker worker){
        return Result.success(workerService.insert(worker));
    }

    @RequestMapping("/update")
    public Result<Boolean> update(@RequestBody Worker worker){
        boolean success = workerService.updateById(worker);
        if(success){
            return Result.success(null);
        }else{
            return Result.fail(501,"您未修改任何数据");
        }
    }
}
