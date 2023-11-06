package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.result.DriverTaskResult;

/**
 * @author zhangdehua
 * @date 2020-02-15
 */
public interface IDriverTaskService {
    void driverGetTask(String taskId, String driverId, String truckId);

    void driverCancelTask(String taskId, String driverId);

    DriverTaskResult getDriverTaskByTaskId(String taskId);
}
