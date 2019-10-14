package top.zdhunter.driverFriend.bean.session;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ELoginType;

@Data
public abstract class LoginSession {
    private String corpId;

    /**
     * 登陆者id
     * @return
     */
    public abstract String loginId();

    /**
     * 登录类型
     * @return
     */
    public abstract ELoginType loginType();
}
