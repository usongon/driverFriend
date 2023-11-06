package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.dao.AdminDao;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.entity.AdminEntity;
import com.usongon.driverFriend.bean.param.InsertAdminParams;
import com.usongon.driverFriend.common.utils.PasswordUtil;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.service.IAdminService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@Service
public class AdminServiceImpl implements IAdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public AdminEntity selAdminByMobile(String mobile) {
        return adminDao.selAdminByMobile(mobile);
    }

    @Override
    public void insertAdmin(InsertAdminParams params) {
        String adminId = UuidUtil.randomUUID();
        String password = PasswordUtil.encode(params.getAdminPassword());
        adminDao.insertAdmin(adminId, params.getAdminName(), password, params.getAdminMobile());
    }

    @Override
    public AdminEntity selAdminByMobileAndPsw(String mobile, String password) {
        return adminDao.selAdminByMobileAndPsw(mobile, password);
    }

    @Override
    public AdminEntity selAdminByAdminId(String adminId) {
        return adminDao.selAdminByAdminId(adminId);
    }
}
