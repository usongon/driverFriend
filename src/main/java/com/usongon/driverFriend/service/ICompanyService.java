package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.param.AdminSelectCompanyParams;
import com.usongon.driverFriend.bean.entity.ChangeCompanyEntity;
import com.usongon.driverFriend.bean.entity.CompanyEntity;
import com.usongon.driverFriend.bean.result.AdminCompanyResult;
import com.usongon.driverFriend.bean.result.CompanyDetailResult;
import com.usongon.driverFriend.enums.ECompanyState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
public interface ICompanyService {
    void addCompany(CompanyEntity entity);

    void changeCompany(ChangeCompanyEntity entity, String companyBoss);

    void changeCompanyState(String companyBoss, String companyId, ECompanyState toBeState);

    List<AdminCompanyResult> adminGetCompanyList(AdminSelectCompanyParams params);

    CompanyEntity getCompanyById(String companyId);

    boolean  getCompanyByBossId(String bossId);

    CompanyDetailResult getCompanyDetailByBossId(String bossId);
}
