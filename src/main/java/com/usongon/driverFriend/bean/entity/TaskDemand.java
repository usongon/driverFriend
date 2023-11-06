package com.usongon.driverFriend.bean.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020/4/28
 */
/**
    * 任务需求表
    */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDemand {
    private Integer id;

    /**
    * 需求id
    */
    private String demandId;

    /**
    * 发布人的id  司机id or 买家id
    */
    private String issueId;

    /**
    * 发布人类型 driver or buyer
    */
    private String issueType;

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

    /**
    * 更新时间
    */
    private Date updateTime;
}