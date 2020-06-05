package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.param.TaskListQueryParams;
import top.zdhunter.driverFriend.bean.result.DriverOverviewResult;
import top.zdhunter.driverFriend.dao.DriverOverviewDao;
import top.zdhunter.driverFriend.service.IDriverOverviewService;
import top.zdhunter.driverFriend.service.ITaskService;
import top.zdhunter.driverFriend.service.ITruckService;

import javax.annotation.Resource;

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
