package com.usongon.driverFriend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@AllArgsConstructor
public enum EUserRole {
    /**
     * Driver 司机
     * Boss 商家
     * Buyer 买家
     */
    Driver ("Driver"),
    Boss ("Boss"),
    Buyer("Buyer");

    @Getter
    String role;
}
