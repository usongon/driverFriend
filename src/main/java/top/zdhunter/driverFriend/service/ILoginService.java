package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.param.LoginParams;
import top.zdhunter.driverFriend.bean.result.LoginResult;

/**
 * @author zhangdehua
 * @date 2019-11-12
 */
public interface ILoginService {
    LoginResult login(LoginParams params);
    void logout(String key);
}
