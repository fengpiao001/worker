package com.apising.worker.service.impl;

import com.apising.worker.domain.Employee;
import com.apising.worker.mapper.EmployeeMapper;
import com.apising.worker.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
