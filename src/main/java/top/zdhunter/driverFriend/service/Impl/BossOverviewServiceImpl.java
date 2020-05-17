package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.DemandListQueryParams;
import top.zdhunter.driverFriend.bean.result.BossOverviewResult;
import top.zdhunter.driverFriend.bean.result.CompanyDetailResult;
import top.zdhunter.driverFriend.dao.BossOverviewDao;
import top.zdhunter.driverFriend.service.BossOverviewService;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.TaskDemandService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020/5/17
 */
@Service
public class BossOverviewServiceImpl implements BossOverviewService {
    @Resource
    private BossOverviewDao bossOverviewDao;
    @Resource
    private ICompanyService companyService;
    @Resource
    private TaskDemandService taskDemandService;
    @Override
    public BossOverviewResult getBossOverview(String bossId){
        CompanyDetailResult companyDetailResult = companyService.getCompanyDetailByBossId(bossId);
        return new BossOverviewResult(bossOverviewDao.getSumIssuedTasks(bossId),
                bossOverviewDao.getSumIssuedGoods(bossId), companyDetailResult.getCompanyId(), companyDetailResult.getCompanyName(),
                companyDetailResult.getCompanyMobile(), companyDetailResult.getCompanyCity(), companyDetailResult.getCompanyAddress(),
                companyDetailResult.getCompanyState(), taskDemandService.getDemandList(new DemandListQueryParams()));

    }
}
