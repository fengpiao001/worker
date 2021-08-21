package com.apising.worker.service;

import com.apising.common.lang.domain.Page;
import com.apising.worker.domain.Worker;
import com.apising.worker.domain.vo.WorkerQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
public interface WorkerService extends IService<Worker> {

    /**
     *  根据特定参数查询
     * @param query
     * @return
     */
    public Page<List<Worker>> query(WorkerQuery query);


    public int insert(Worker worker);

}
