package com.apising.worker.service.impl;

import com.apising.common.lang.domain.Page;
import com.apising.common.lang.util.StringUtil;
import com.apising.worker.domain.Worker;
import com.apising.worker.domain.vo.WorkerQuery;
import com.apising.worker.mapper.WorkerMapper;
import com.apising.worker.service.WorkerService;
import com.apising.worker.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
@Service
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {
    @Autowired
    WorkerMapper workerMapper;

    @Override
    public Page<List<Worker>> query(WorkerQuery query) {
        QueryWrapper<Worker> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(query.getMobile()),"mobile", query.getMobile());
        queryWrapper.eq(StringUtils.isNotEmpty(query.getRealName()),"real_name", query.getRealName());
        Page<List<Worker>> page = PageUtil.mybatisPageToPage(workerMapper.selectPage(PageUtil.pageToMybatisPage(query),queryWrapper));
        return page;
    }

    @Override
    public int insert(Worker worker) {
        Assert.notNull(worker,"工人信息不能为空");
        Assert.state(StringUtils.isNotEmpty(worker.getRealName()),"工人姓名不能为空");
        Assert.state(StringUtils.isNotEmpty(worker.getBankNo()),"银行卡号不能为空");
        Assert.state(StringUtils.isNotEmpty(worker.getBankName()),"银行名称不能为空");

        return workerMapper.insert(worker);
    }
}
