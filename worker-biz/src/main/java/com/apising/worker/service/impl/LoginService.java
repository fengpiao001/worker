package com.apising.worker.service.impl;


import com.apising.common.lang.exception.XException;
import com.apising.common.lang.session.SessionDto;
import com.apising.common.lang.util.PasswordUtil;
import com.apising.common.lang.util.SessionUtil;
import com.apising.worker.config.SessionHandler;
import com.apising.worker.domain.Employee;
import com.apising.worker.domain.UserLogin;
import com.apising.worker.domain.Worker;
import com.apising.worker.domain.enums.BaseEnum;
import com.apising.worker.domain.enums.UserType;
import com.apising.worker.domain.vo.LoginReq;
import com.apising.worker.mapper.EmployeeMapper;
import com.apising.worker.mapper.UserLoginMapper;
import com.apising.worker.mapper.WorkerMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 登录service
 * @author
 */
@Component
public class LoginService {

    @Autowired
    UserLoginMapper userLoginMapper;
    @Autowired
    WorkerMapper workerMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SessionHandler sessionHandler;

    /**
     * 根据类型登录
     * @param loginReq
     * @return
     */
    public SessionDto loginByType(LoginReq loginReq){
        Assert.state(StringUtils.isNotEmpty(loginReq.getMobile())
        && StringUtils.isNotEmpty(loginReq.getPassword())
        && BaseEnum.valueOfIndex(UserType.class,loginReq.getUserType()) != null,
                "参数缺失或不正确");
        UserLogin userLogin = this.queryByMobileAndUserType(loginReq.getMobile(),loginReq.getUserType());
        if(userLogin == null){
            throw new XException("userLogin is null").setShowText("该用户不存在");
        }
        if(!checkPassword(userLogin.getPassword(),loginReq.getPassword())){
            throw new XException("password is error").setShowText("密码不正确");
        }
        String sessionId = SessionUtil.createSessionId(userLogin.getMobile(),null);
        SessionDto sessionDto = createSessionDto(userLogin,sessionId);
        if(UserType.admin.equal(loginReq.getUserType())){
            sessionDto.setUserName("管理员");
        }else if(UserType.worker.equal(loginReq.getUserType())){
            Worker worker = workerMapper.selectById(sessionDto.getUserId());
            if(worker == null){
                throw new XException().setShowText("工人信息不存在");
            }
            sessionDto.setUserName(worker.getRealName());
        }else if(UserType.employee.equal(loginReq.getUserType())){
            Employee employee = employeeMapper.selectById(sessionDto.getUserId());
            if(employee == null){
                throw new XException().setShowText("员工信息不存在");
            }
            sessionDto.setUserName(employee.getRealName());
        }
        sessionHandler.setSession(sessionDto);
        return sessionDto;

    }



    /**
     * 根据手机号和用户类型查询登录信息
     * @param mobile
     * @param userType
     * @return
     */
    public UserLogin queryByMobileAndUserType(String mobile,Integer userType){
        QueryWrapper<UserLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        queryWrapper.eq("user_type",userType);
        List<UserLogin> userLoginList = userLoginMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(userLoginList)){
            return null;
        }
        return userLoginList.get(0);

    }

    /**
     * 校验密码是否正确
     * @param dbPassword 数据库的密码
     * @param inPassword 前端传入的密码（未进行md5）
     * @return
     */
    private boolean checkPassword(String dbPassword,String inPassword){
        if(StringUtils.isEmpty(dbPassword) || StringUtils.isEmpty(inPassword)){
            return false;
        }
        String inSecretPassword = PasswordUtil.getSecretPassword(inPassword,null);
        return dbPassword.equals(inSecretPassword);
    }

    /**
     * 创建一个session对象
     * @param userLogin
     * @param sessionId
     * @return
     */
    private SessionDto createSessionDto(UserLogin userLogin,String sessionId){
        SessionDto sessionDto = new SessionDto();
        sessionDto.setUserType(userLogin.getUserType());
        sessionDto.setId(userLogin.getId());
        sessionDto.setUserId(userLogin.getUserId());
        sessionDto.setSessionId(sessionId);
        sessionDto.setMobile(userLogin.getMobile());
        return sessionDto;
    }
}
