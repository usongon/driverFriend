package com.usongon.driverFriend.bean.entity;

import com.usongon.driverFriend.enums.EUserRole;
import com.usongon.driverFriend.enums.EUserState;
import lombok.Data;

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
