package com.usongon.driverFriend.bean.session;

import com.usongon.driverFriend.enums.ELoginType;
import lombok.Data;

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
