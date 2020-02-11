package top.zdhunter.driverFriend.bean.param;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Data
public class ChangeTaskParam {
    private String taskId;
    private String cargoKind;
    private float cargoWeight;
    private String taskDeadline;
    private String destinationCity;
    private String destinationAddress;
    private String remark;
}
