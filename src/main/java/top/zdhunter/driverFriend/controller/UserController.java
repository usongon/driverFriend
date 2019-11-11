package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.ELoginType;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserState;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
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

    @PostMapping("/admin/changeUserState")
    Object changeUserState(String userId, EUserState toBeState){
        AdminSession adminSession = GlobalHelper.get();
        if (!adminSession.loginType().equals(ELoginType.Admin)){
            throw new BusinessException(EResponseCode.BizError, "只有管理员可以进行这个操作", "");
        }
        userService.changeUserState(userId, toBeState);
        return ResponseResult.success();
    }
}
