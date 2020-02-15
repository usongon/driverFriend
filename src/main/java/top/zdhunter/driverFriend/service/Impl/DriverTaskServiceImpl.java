package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.result.DriverTaskResult;
import top.zdhunter.driverFriend.dao.DriverTaskDao;
import top.zdhunter.driverFriend.service.IDriverTaskService;

import javax.annotation.Resource;

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
