package top.zdhunter.driverFriend.framework;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.exception.BusinessException;

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
