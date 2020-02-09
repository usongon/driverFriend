package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.ELoginType;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserState;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

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
}
