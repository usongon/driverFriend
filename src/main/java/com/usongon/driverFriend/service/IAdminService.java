package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.entity.AdminEntity;
import com.usongon.driverFriend.bean.param.InsertAdminParams;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
public interface IAdminService {
    AdminEntity selAdminByMobile(String mobile);
    void insertAdmin(InsertAdminParams params);
    AdminEntity selAdminByMobileAndPsw(String mobile, String password);

    AdminEntity selAdminByAdminId(String adminId);
}
