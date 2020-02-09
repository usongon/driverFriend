package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.bean.param.AdminSelectCompanyParams;
import top.zdhunter.driverFriend.bean.result.AdminCompanyResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
public interface ICompanyService {
    void addCompany(CompanyEntity entity);

    void changeCompany(ChangeCompanyEntity entity, String companyBoss);

    void deleteCompany(String companyBoss, String companyId);

    List<AdminCompanyResult> adminGetCompanyList(AdminSelectCompanyParams params);
}
