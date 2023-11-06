package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.bean.param.LoginParams;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.usongon.driverFriend.bean.entity.AdminEntity;
import com.usongon.driverFriend.bean.entity.UserEntity;
import com.usongon.driverFriend.bean.result.LoginResult;
import com.usongon.driverFriend.bean.session.AdminSession;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.utils.PasswordUtil;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.enums.ELoginType;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.EUserState;
import com.usongon.driverFriend.redis.SessionRedis;
import com.usongon.driverFriend.service.IAdminService;
import com.usongon.driverFriend.service.ILoginService;
import com.usongon.driverFriend.service.IUserService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-12
 */
@Log4j2
@Service
public class LoginServiceImpl implements ILoginService {
    @Resource
    private IAdminService adminService;
    @Resource
    private IUserService userService;
    @Resource
    private SessionRedis sessionRedis;
    @Override
    @Authorize(login = false)
    public LoginResult adminLogin(LoginParams params) {
        AdminEntity admin = adminService.selAdminByMobile(params.getMobile());
        if (admin == null){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!PasswordUtil.match(params.getPassword(), admin.getAdminPassword())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        AdminSession session = new AdminSession();
        session.setAdminId(admin.getAdminId());
        String uuid = "admin:" + UuidUtil.randomUUID();
        sessionRedis.setSession(uuid, session);
        return new LoginResult(ELoginType.Admin, uuid, admin.getAdminName(), "admin");
    }

    @Override
    public LoginResult userLogin(LoginParams params) {
        UserEntity user = userService.selUserByMobile(params.getMobile());
        if (user == null){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!PasswordUtil.match(params.getPassword(), user.getUserPassword())){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (user.getUserState().equals(EUserState.Del)){
            throw new BusinessException(EResponseCode.BizError, "账号或密码错误", "");
        }
        if (!user.getUserState().equals(EUserState.On)){
            throw new BusinessException(EResponseCode.BizError, "您的账号未在启用状态", "");
        }
        UserSession session = new UserSession();
        session.setUserId(user.getUserId());
        session.setRole(user.getUserRole());
        String uuid = "user:" + UuidUtil.randomUUID();
        sessionRedis.setSession(uuid, session);
        return new LoginResult(ELoginType.User, uuid, user.getUserName(), user.getUserRole().name());
    }

    @Override
    public void logout(String key) {
        if (!StringUtils.isEmpty(key)){
            sessionRedis.delete(key);
        }
    }
}
