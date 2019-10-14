package top.zdhunter.driverFriend.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.zdhunter.driverFriend.enums.EResponseCode;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {
    private int code;
    private String msg;
    private Object data;

    /**
     * 响应成功
     *
     * @return
     */
    public static ResponseResult success() {
        return new ResponseResult(EResponseCode.Ok.getCode(), "", "");
    }

    /**
     * 响应成功
     *
     * @param data
     * @return
     */
    public static ResponseResult success(Object data) {
        return new ResponseResult(EResponseCode.Ok.getCode(), "", data);
    }
}

