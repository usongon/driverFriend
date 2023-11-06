package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.result.DriverOverviewResult;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
public interface IDriverOverviewService {
    DriverOverviewResult getDriverOverviewMsg(String driverId);
}
