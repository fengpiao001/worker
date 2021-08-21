package com.apising.worker.controller;


import com.apising.common.lang.domain.Result;
import com.apising.common.lang.exception.XException;
import com.apising.worker.domain.UserLogin;
import com.apising.worker.service.UserLoginService;
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
@RequestMapping("/user-login")
public class UserLoginController {
    @Autowired
    UserLoginService userLoginService;

    @RequestMapping("/list")
    public Result<List<UserLogin>> list(){
        return Result.success(userLoginService.list());
    }

    @RequestMapping("/insert")
    public Result<Integer> insert(@RequestBody UserLogin userLogin){
        return Result.success(userLoginService.createUserLogin(userLogin));
    }
}
