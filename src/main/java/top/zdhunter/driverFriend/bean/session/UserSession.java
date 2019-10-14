package top.zdhunter.driverFriend.bean.session;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ELoginType;

@Data
public class UserSession extends LoginSession {
    private String userId;

    @Override
    public String loginId() {
        return userId;
    }

    @Override
    public ELoginType loginType() {
        return ELoginType.User;
    }
}
