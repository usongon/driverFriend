package top.zdhunter.driverFriend.bean.param;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-02-12
 */
@Data
public class TaskListQueryParams {
    private String issueId;
    private String companyId;
    private float minWeight = 0;
    private float maxWeight = Float.MAX_VALUE;
    private String destinationCity;
    private String taskState;
}
