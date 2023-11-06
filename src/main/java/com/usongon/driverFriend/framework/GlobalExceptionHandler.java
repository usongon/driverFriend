package com.usongon.driverFriend.framework;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.framework.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.usongon.driverFriend.enums.EResponseCode;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler
    public Object processException(Exception e) {
        if (e instanceof BusinessException) {
            return new ResponseResult(((BusinessException) e).getCode().getCode(),
                    ((BusinessException) e).getMsg(), ((BusinessException) e).getData());
        }
        log.error(e.getMessage(), e);
        return new ResponseResult(EResponseCode.SysError.getCode(), "系统错误", "");
    }
}
