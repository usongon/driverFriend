package com.usongon.driverFriend.bean.session;

import com.usongon.driverFriend.enums.ELoginType;
import com.usongon.driverFriend.enums.EUserRole;
import lombok.Data;

/**
 * @author hunter
 */
@Data
public class UserSession extends LoginSession {
    private String userId;
    private EUserRole role;

    @Override
    public String loginId() {
        return userId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.User;
    }
}
