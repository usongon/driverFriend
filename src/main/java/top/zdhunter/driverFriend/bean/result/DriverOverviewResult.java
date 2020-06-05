package top.zdhunter.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.zdhunter.driverFriend.enums.ETaskState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
@Data
@AllArgsConstructor
public class DriverOverviewResult {
    private int sumGetTask;
    private int sumIssueDemand;
    private TruckResult truck;
    private List<TaskResult> task;
}
