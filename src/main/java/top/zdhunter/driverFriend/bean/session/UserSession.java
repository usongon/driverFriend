package top.zdhunter.driverFriend.bean.session;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ELoginType;
import top.zdhunter.driverFriend.enums.EUserRole;

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
