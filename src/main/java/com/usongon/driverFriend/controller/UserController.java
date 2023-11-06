package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.entity.UserEntity;
import com.usongon.driverFriend.bean.session.AdminSession;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.ELoginType;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.EUserState;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import com.usongon.driverFriend.service.IUserService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Authorize
@RestController
public class UserController {
    @Resource
    private IUserService userService;
    @Authorize(login = false)
    @PostMapping("/register")
    public Object registerUser(UserEntity entity){
        userService.registerUser(entity);
        return ResponseResult.success();
    }

    @PostMapping("/admin/changeUserState")
    public Object changeUserState(String userId, EUserState toBeState){
        AdminSession adminSession = GlobalHelper.get();
        if (!adminSession.loginType().equals(ELoginType.Admin)){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以进行这个操作", "");
        }
        userService.changeUserState(userId, toBeState);
        return ResponseResult.success();
    }

    @PostMapping("/user/update")
    public Object updateUser(String userName, String userMobile){
        UserSession session = GlobalHelper.get();
        userService.updateUser(session.getUserId(), userName, userMobile);
        return ResponseResult.success();
    }

    @PostMapping("/user/changepsw")
    public Object changePassword(String oldPassword, String newPassword1, String newPassword2){
        UserSession session = GlobalHelper.get();
        if (!newPassword1.equals(newPassword2)){
            throw new BusinessException(EResponseCode.BizError, "两次密码输入不一样，请重新输入", "");
        }
        userService.changePassword(session.getUserId(), oldPassword, newPassword1);
        return ResponseResult.success();
    }

    @PostMapping("/user/detail")
    public Object getUserDetail(){
        UserSession session = GlobalHelper.get();
        return ResponseResult.success(userService.getUserDetail(session.getUserId()));
    }
}
