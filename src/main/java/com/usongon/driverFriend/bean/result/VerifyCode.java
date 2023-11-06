package com.usongon.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCode {
    private String verifyKey;
    private String verifyCode;
}
