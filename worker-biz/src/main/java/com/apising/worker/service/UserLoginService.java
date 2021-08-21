package com.apising.worker.service;

import com.apising.worker.domain.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fengpiao
 * @since 2021-08-17
 */
public interface UserLoginService extends IService<UserLogin> {

    /**
     * 插入登录信息
     * @param userLogin
     * @return
     */
    public int createUserLogin(UserLogin userLogin);

}
