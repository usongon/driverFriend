package top.zdhunter.driverFriend.bean.result;

import lombok.Data;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.enums.EUserState;

/**
 * @author zhangdehua
 * @date 2020-02-09
 */
@Data
public class AdminUserResult {
    String userId;
    String userName;
    String userMobile;
    EUserRole userRole;
    EUserState userState;
}
