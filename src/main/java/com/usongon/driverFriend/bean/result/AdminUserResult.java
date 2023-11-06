package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.EUserRole;
import com.usongon.driverFriend.enums.EUserState;
import lombok.Data;

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
