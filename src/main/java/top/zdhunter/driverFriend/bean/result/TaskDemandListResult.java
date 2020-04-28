package top.zdhunter.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.zdhunter.driverFriend.enums.EUserRole;

import java.util.Date;

/**
    * 任务需求表
 * @author zhangdehua
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDemandListResult {
    /**
     * 需求Id
     */
    private String demandId;

    /**
    * 发布人类型 driver or buyer
    */
    private EUserRole issueType;

    /**
    * 需求名称
    */
    private String demandName;


    /**
    * 起点城市
    */
    private String demandStart;

    /**
    * 重点城市
    */
    private String demandDestination;
}