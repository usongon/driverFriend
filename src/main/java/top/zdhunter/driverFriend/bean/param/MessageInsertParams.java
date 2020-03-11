package top.zdhunter.driverFriend.bean.param;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-03-11
 */
@Data
public class MessageInsertParams {
    String senderId;
    String receiverId;
    String content;
    Integer action;
    /**
     * 目标任务Id
     */
    String target;
}
