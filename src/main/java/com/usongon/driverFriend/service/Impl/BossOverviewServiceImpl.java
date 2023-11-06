package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.dao.BossOverviewDao;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.param.DemandListQueryParams;
import com.usongon.driverFriend.bean.result.BossOverviewResult;
import com.usongon.driverFriend.bean.result.CompanyDetailResult;
import com.usongon.driverFriend.service.BossOverviewService;
import com.usongon.driverFriend.service.ICompanyService;
import com.usongon.driverFriend.service.TaskDemandService;

import jakarta.annotation.Resource;

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
