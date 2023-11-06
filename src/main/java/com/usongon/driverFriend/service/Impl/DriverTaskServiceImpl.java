package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.dao.DriverTaskDao;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.result.DriverTaskResult;
import com.usongon.driverFriend.service.IDriverTaskService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-02-15
 */
@Service
public class DriverTaskServiceImpl implements IDriverTaskService {
    @Resource
    private DriverTaskDao driverTaskDao;
    @Override
    public void driverGetTask(String taskId, String driverId, String truckId) {
        driverTaskDao.driverGetTask(taskId, driverId, truckId);
    }

    @Override
    public void driverCancelTask(String taskId, String driverId) {
        driverTaskDao.driverCancelTask(taskId, driverId);
    }

    @Override
    public DriverTaskResult getDriverTaskByTaskId(String taskId) {
        return driverTaskDao.getDriverTaskByTaskId(taskId);
    }
}
