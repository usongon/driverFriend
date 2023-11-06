package com.usongon.driverFriend.bean.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Data
public class TaskEntity {
    private String taskId;
    private String issueId;
    private String companyId;
    private String cargoKind;
    private float cargoWeight;
    private LocalDateTime taskDeadline;
    private String destinationCity;
    private String destinationAddress;
    private String remark;
}
