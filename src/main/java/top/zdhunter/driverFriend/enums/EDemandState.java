package top.zdhunter.driverFriend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
@AllArgsConstructor
public enum EDemandState {
    /**
     * Wait 等待中
     * Finished 完结
     * Del 已删除
     */

    Wait ("Wait"),
    Finished("Finished"),
    Del ("Del");

    @Getter
    private final String state;
}
