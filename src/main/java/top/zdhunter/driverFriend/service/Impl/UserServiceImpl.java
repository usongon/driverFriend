package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.UserDao;
import top.zdhunter.driverFriend.enums.EUserState;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;
    @Override
    public void registerUser(UserEntity entity) {
        entity.setUserId(UuidUtil.randomUUID());
        entity.setUserPassword(PasswordUtil.encode(entity.getUserPassword()));
        userDao.registerUser(entity.getUserId(), entity.getUserName(), entity.getUserMobile(), entity.getUserPassword(), entity.getUserRole());
    }

    @Override
    public void changeUserState(String userId, EUserState toBeState) {
        userDao.changeUserState(userId, toBeState.toString());
    }

    @Override
    public UserEntity selUserByMobile(String mobile) {
        return userDao.selUserByMobile(mobile);
    }
}
