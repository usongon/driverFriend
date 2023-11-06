package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.ELoginType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangdehua
 * @date 2019-11-12
 */
@Data
@AllArgsConstructor
public class LoginResult {
    private ELoginType loginType;
    private String token;
    private String name;
    private String role;
}
