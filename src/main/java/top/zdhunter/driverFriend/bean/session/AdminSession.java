package top.zdhunter.driverFriend.bean.session;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ELoginType;

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
