package top.zdhunter.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020/5/17
 */
@Data
@AllArgsConstructor
public class BossOverviewResult {
    int sumIssuedTasks;
    int sumIssuedGoods;
    String companyId;
    String companyName;
    String companyMobile;
    String companyCity;
    String companyAddress;
    String companyState;
    List<TaskDemandListResult> demand;
}
