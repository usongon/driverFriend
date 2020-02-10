package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.bean.result.AdminUserResult;
import top.zdhunter.driverFriend.enums.EUserState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
public interface IUserService {
    void registerUser(UserEntity entity);

    void changeUserState(String userId, EUserState toBeState);

    UserEntity selUserByMobile(String mobile);

    UserEntity selUserById(String userId);
    void updateUser(String userId, String userName, String userMobile);

    void changePassword(String userId, String oldPassword, String newPassword);

    List<AdminUserResult> adminGetAllUser(String keywords, String userState);
}
