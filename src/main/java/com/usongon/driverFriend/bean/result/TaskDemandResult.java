package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.EUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
    * 任务需求表
 * @author zhangdehua
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDemandResult {
    /**
     * 需求Id
     */
    private String demandId;

    /**
    * 发布人的id  司机id or 买家id
    */
    private String issueId;

    /**
     * 发布人的姓名
     */
    private String issueName;

    /**
     * 发布人的手机号
     */
    private String issueMobile;

    /**
    * 发布人类型 driver or buyer
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