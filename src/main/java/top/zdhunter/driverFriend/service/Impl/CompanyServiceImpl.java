package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.CompanyDao;
import top.zdhunter.driverFriend.service.ICompanyService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
@Service
public class CompanyServiceImpl implements ICompanyService {
    @Resource
    private CompanyDao companyDao;
    @Override
    public void addCompany(CompanyEntity entity) {
        entity.setCompanyId(UuidUtil.randomUUID());
        companyDao.addCompany(entity);
    }

    @Override
    public void changeCompany(ChangeCompanyEntity entity, String companyBoss) {
        entity.setCompanyBoss(companyBoss);
        companyDao.changeCompany(entity);
    }
}
