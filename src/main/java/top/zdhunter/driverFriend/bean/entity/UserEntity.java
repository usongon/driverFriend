package top.zdhunter.driverFriend.bean.entity;

import lombok.Data;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.enums.EUserState;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Data
public class UserEntity {
    private String userId = "default";
    private String userName;
    private String userMobile;
    private String userPassword;
    private EUserRole userRole;
    private EUserState userState;
}
