package com.apising.worker.controller;

import com.apising.common.lang.domain.Result;
import com.apising.common.lang.session.SessionDto;
import com.apising.worker.domain.vo.LoginReq;
import com.apising.worker.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/loginByPassword")
    public Result<SessionDto> loginByPassword(@RequestBody LoginReq loginReq){
        return Result.success(loginService.loginByType(loginReq));
    }


}
