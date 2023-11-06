package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.bean.param.AdminSelectCompanyParams;
import com.usongon.driverFriend.dao.CompanyDao;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.entity.ChangeCompanyEntity;
import com.usongon.driverFriend.bean.entity.CompanyEntity;
import com.usongon.driverFriend.bean.result.AdminCompanyResult;
import com.usongon.driverFriend.bean.result.CompanyDetailResult;
import com.usongon.driverFriend.common.helper.ParamsHelper;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.enums.ECompanyState;
import com.usongon.driverFriend.service.ICompanyService;

import jakarta.annotation.Resource;
import java.util.List;

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

    @Override
    public void changeCompanyState(String companyBoss, String companyId, ECompanyState toBeState) {
        companyDao.changeCompanyState(companyBoss, companyId, toBeState.toString());
    }

    @Override
    public List<AdminCompanyResult> adminGetCompanyList(AdminSelectCompanyParams params) {
        return companyDao.adminSelectCompanyList(
                ParamsHelper.processStrSearchParams(params.getCompanyBoss()),
                ParamsHelper.processStrSearchParams(params.getCompanyName()),
                ParamsHelper.processStrSearchParams(params.getCompanyMobile()),
                ParamsHelper.processStrSearchParams(params.getCompanyCity()),
                ParamsHelper.processStrSearchParams(params.getCompanyAddress()),
                ParamsHelper.processStrSearchParams(params.getCompanyState()));
    }

    @Override
    public CompanyEntity getCompanyById(String companyId) {
        return companyDao.getCompanyById(companyId);
    }

    @Override
    public boolean getCompanyByBossId(String bossId) {
        return companyDao.getCompanyByBossId(bossId) != null;
    }

    @Override
    public CompanyDetailResult getCompanyDetailByBossId(String bossId) {
        return companyDao.getCompanyDetailByBossId(bossId);
    }
}
