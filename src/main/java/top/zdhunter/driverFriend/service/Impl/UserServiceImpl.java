package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.UserDao;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserState;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
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
        if (userDao.selUserByMobile(entity.getUserMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "该手机号已注册", "");
        }
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

    @Override
    public void updateUser(String userId, String userName, String userMobile) {
        if (userDao.selUserByMobile(userMobile) != null && !userDao.selUserByMobile(userMobile).getUserId().equals(userId)){
            throw new BusinessException(EResponseCode.BizError, "该手机号已注册", "");
        }
        userDao.updateUser(userId, userName, userMobile);
    }

    @Override
    public void changePassword(String userId, String oldPassword, String newPassword) {
        if (!PasswordUtil.match(oldPassword, userDao.selUserById(userId).getUserPassword())){
            throw new BusinessException(EResponseCode.BizError, "原密码输入错误", "");
        }
        userDao.changePassword(userId, PasswordUtil.encode(newPassword));
    }
}
