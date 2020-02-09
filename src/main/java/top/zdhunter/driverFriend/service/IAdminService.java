package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.AdminEntity;
import top.zdhunter.driverFriend.bean.param.InsertAdminParams;

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
