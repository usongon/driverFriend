package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.LoginParams;
import top.zdhunter.driverFriend.service.ILoginService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@RestController
public class LoginController {
    @Resource
    private ILoginService loginService;

    @PostMapping("/admin/login")
    public Object adminLogin(LoginParams params){
        return ResponseResult.success(loginService.adminLogin(params));
    }

    @PostMapping("/user/login")
    public Object userLogin(LoginParams params){
        return ResponseResult.success(loginService.userLogin(params));
    }

}
