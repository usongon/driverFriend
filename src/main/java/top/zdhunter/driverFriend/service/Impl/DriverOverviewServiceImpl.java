package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.result.DriverOverviewResult;
import top.zdhunter.driverFriend.dao.DriverOverviewDao;
import top.zdhunter.driverFriend.service.IDriverOverviewService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
@Service
public class DriverOverviewServiceImpl implements IDriverOverviewService {
    @Resource
    private DriverOverviewDao driverOverviewDao;
    @Override
    public DriverOverviewResult getDriverOverviewMsg(String driverId) {
        return driverOverviewDao.getDriverOverview(driverId);
    }
}
