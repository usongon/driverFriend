package com.usongon.driverFriend.bean.session;

import com.usongon.driverFriend.enums.ELoginType;
import lombok.Data;

@Data
public class AdminSession extends LoginSession {
    private String adminId;

    @Override
    public String loginId() {
        return adminId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.Admin;
    }
}
