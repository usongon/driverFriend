package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.bean.param.LoginParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.service.ILoginService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@RestController
public class LoginController {
    @Resource
    private ILoginService loginService;

    @Authorize(login = false)
    @PostMapping("/admin/login")
    public Object adminLogin(LoginParams params){
        return ResponseResult.success(loginService.adminLogin(params));
    }

    @Authorize(login = false)
    @PostMapping("/user/login")
    public Object userLogin(LoginParams params){
        return ResponseResult.success(loginService.userLogin(params));
    }

    @Authorize
    @PostMapping("/logout")
    public Object logout(@RequestHeader(name = "token") String token){
        loginService.logout(token);
        return ResponseResult.success();
    }

}
