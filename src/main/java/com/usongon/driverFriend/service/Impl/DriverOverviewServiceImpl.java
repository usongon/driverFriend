package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.dao.DriverOverviewDao;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.result.DriverOverviewResult;
import com.usongon.driverFriend.service.IDriverOverviewService;
import com.usongon.driverFriend.service.ITaskService;
import com.usongon.driverFriend.service.ITruckService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
@Service
public class DriverOverviewServiceImpl implements IDriverOverviewService {
    @Resource
    private DriverOverviewDao driverOverviewDao;
    @Resource
    private ITruckService truckService;
    @Resource
    private ITaskService taskService;
    @Override
    public DriverOverviewResult getDriverOverviewMsg(String driverId) {
        return new DriverOverviewResult(driverOverviewDao.sumGetTask(driverId),
                driverOverviewDao.sumIssueDemand(driverId), truckService.getTruckDetailByDriverId(driverId),
                taskService.getTaskListByDriver(driverId));
    }
}
