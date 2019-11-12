package top.zdhunter.driverFriend.service.Impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.zdhunter.driverFriend.bean.entity.AdminEntity;
import top.zdhunter.driverFriend.bean.param.LoginParams;
import top.zdhunter.driverFriend.bean.result.LoginResult;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.enums.ELoginType;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.redis.SessionRedis;
import top.zdhunter.driverFriend.service.IAdminService;
import top.zdhunter.driverFriend.service.ILoginService;

import javax.annotation.Resource;

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
    private SessionRedis sessionRedis;
    @Override
    @Authorize(login = false)
    public LoginResult login(LoginParams params) {
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
    public void logout(String key) {
        if (!StringUtils.isEmpty(key)){
            sessionRedis.delete(key);
        }
    }
}
