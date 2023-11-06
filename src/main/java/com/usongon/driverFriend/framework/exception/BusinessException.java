package com.usongon.driverFriend.framework.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.usongon.driverFriend.enums.EResponseCode;

/**
 * 业务异常，该异常允许把信息msg暴露给用户
 */
@AllArgsConstructor
@Data
public class BusinessException extends RuntimeException {
    private EResponseCode code;
    private String msg;
    private Object data;
}
