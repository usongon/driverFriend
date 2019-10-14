package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@RestController
public class UserController {
    @Resource
    private IUserService userService;
    @PostMapping("/user/register")
    Object registerUser(UserEntity entity){
        userService.registerUser(entity);
        return ResponseResult.success();
    }
}
