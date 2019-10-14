package top.zdhunter.driverFriend.common.utils;

import java.util.UUID;

public class UuidUtil {

    /**
     * 生成UUID
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
