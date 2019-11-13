package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.LoginParams;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
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
