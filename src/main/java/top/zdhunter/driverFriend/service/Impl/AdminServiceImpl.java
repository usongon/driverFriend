package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.AdminEntity;
import top.zdhunter.driverFriend.bean.param.InsertAdminParams;
import top.zdhunter.driverFriend.common.utils.PasswordUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.AdminDao;
import top.zdhunter.driverFriend.service.IAdminService;

import javax.annotation.Resource;

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
}
