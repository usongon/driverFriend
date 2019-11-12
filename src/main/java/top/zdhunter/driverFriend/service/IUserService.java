package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.enums.EUserState;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
public interface IUserService {
    void registerUser(UserEntity entity);

    void changeUserState(String userId, EUserState toBeState);

    UserEntity selUserByMobile(String mobile);
}
