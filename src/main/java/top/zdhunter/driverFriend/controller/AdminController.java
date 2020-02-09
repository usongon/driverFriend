package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.InsertAdminParams;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.IAdminService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@RestController
public class AdminController {
    @Resource
    private IAdminService adminService;

    @PostMapping("/admin/insert")
    Object insertAdmin(InsertAdminParams params){
        if (adminService.selAdminByMobile(params.getAdminMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号已注册", "");
        }
        adminService.insertAdmin(params);
        return ResponseResult.success();
    }

    //TODO 管理员审核模块
}
