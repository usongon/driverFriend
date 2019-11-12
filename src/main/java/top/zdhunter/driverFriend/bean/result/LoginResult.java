package top.zdhunter.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.zdhunter.driverFriend.enums.ELoginType;

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
