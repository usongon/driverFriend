package top.zdhunter.driverFriend.bean.result;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ETaskState;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
@Data
public class DriverOverviewResult {
    private String driverName;
    private String truckNumber;
    private ETaskState taskState;
    private String taskId;
}
