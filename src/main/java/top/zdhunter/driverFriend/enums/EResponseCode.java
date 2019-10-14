package top.zdhunter.driverFriend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EResponseCode {
    Ok          (0),   // 正确响应

    NoLogin     (100), // 未登录
    LoginError  (101), // 登录失败
    BadCsrf     (102), // CSRF验证失败
    PermissionDenied (103), // 无权限

    NoLock      (111), // 未获取到锁
    LockExpired (112), // 锁过期

    InvalidFrom (201), // 表单验证错误

    SysError    (500), // 系统错误 // 系统预期外的异常，前端页面直接显示系统错误就可以
    BizError    (501); // 业务错误 // 业务捕捉到的异常，可以返回错误信息直接展示给用户


    @Getter
    private int code;
}
