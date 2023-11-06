package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.dao.UserDao;
import com.usongon.driverFriend.framework.exception.BusinessException;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.entity.UserEntity;
import com.usongon.driverFriend.bean.result.AdminUserResult;
import com.usongon.driverFriend.bean.result.UserResult;
import com.usongon.driverFriend.common.helper.ParamsHelper;
import com.usongon.driverFriend.common.utils.PasswordUtil;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.EUserState;
import com.usongon.driverFriend.service.IUserService;

import jakarta.annotation.Resource;
import java.util.List;

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
    public UserEntity selUserById(String userId) {
        return userDao.selUserById(userId);
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

    @Override
    public List<AdminUserResult> adminGetAllUser(String keywords, String userState) {
        keywords = ParamsHelper.processStrSearchParams(keywords);
        userState = ParamsHelper.processStrSearchParams(userState);
        return userDao.adminGetAllUser(keywords, userState);
    }

    @Override
    public UserResult getUserDetail(String userId) {
        return userDao.getUserDetail(userId);
    }
}
