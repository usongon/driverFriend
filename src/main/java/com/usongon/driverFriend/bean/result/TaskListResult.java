package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.ETaskState;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Data
public class TaskListResult {
    private String taskId;
    private String issueId;
    private String issueName;            //显示
    private String companyId;
    private String companyName;          //显示
    private String cargoKind;            //显示
    private float cargoWeight;           //显示
    private LocalDateTime taskDeadline;  //显示
    private String startCity;
    private String destinationCity;      //显示
    private ETaskState taskState;        //显示
}
