package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
public interface ICompanyService {
    void addCompany(CompanyEntity entity);

    void changeCompany(ChangeCompanyEntity entity, String companyBoss);
}
