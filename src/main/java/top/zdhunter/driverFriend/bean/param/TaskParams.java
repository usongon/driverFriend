package top.zdhunter.driverFriend.bean.param;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Data
@AllArgsConstructor
public class TaskParams {
    private String companyId;
    private String cargoKind;
    private float cargoWeight;
    private String taskDeadline;
    private String destinationCity;
    private String destinationAddress;
    private String remark = "无";
}
