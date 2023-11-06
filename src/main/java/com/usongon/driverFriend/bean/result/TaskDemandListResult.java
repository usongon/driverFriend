package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.EUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 需求状态
     */
    private String demandState;
}