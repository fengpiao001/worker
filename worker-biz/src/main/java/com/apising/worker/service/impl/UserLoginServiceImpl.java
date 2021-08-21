package com.apising.worker.service.impl;

import com.apising.common.lang.util.PasswordUtil;
import com.apising.worker.domain.UserLogin;
import com.apising.worker.mapper.UserLoginMapper;
import com.apising.worker.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;
    /**
     * 插入登录信息
     *
     * @param userLogin
     * @return
     */
    @Override
    public int createUserLogin(UserLogin userLogin) {
        String secretPassword = PasswordUtil.getSecretPassword(userLogin.getPassword(),null);
        userLogin.setPassword(secretPassword);
        return userLoginMapper.insert(userLogin);
    }
}
