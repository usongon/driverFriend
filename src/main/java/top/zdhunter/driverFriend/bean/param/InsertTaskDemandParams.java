package top.zdhunter.driverFriend.bean.param;

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
public class InsertTaskDemandParams {
    /**
    * 发布人的id  司机id or 买家id
    */
    private String issueId;

    /**
    * 发布人类型 driver or buyer or boss
    */
    private EUserRole issueType;

    /**
    * 需求名称
    */
    private String demandName;

    /**
    * 需求重量 买家任意填，司机只能填写比自己车承载量小的数字
    */
    private Integer demandWeight;

    /**
    * 起点城市
    */
    private String demandStart;

    /**
    * 重点城市
    */
    private String demandDestination;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * Wait-等待 Finished-已完成 Del-已删除
    */
    private String demandState;
}