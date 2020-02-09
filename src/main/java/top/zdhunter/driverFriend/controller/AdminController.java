package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.AdminSelectCompanyParams;
import top.zdhunter.driverFriend.bean.param.InsertAdminParams;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.ECompanyState;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.IAdminService;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@Authorize
@RestController
public class AdminController {
    @Resource
    private IAdminService adminService;
    @Resource
    private ICompanyService companyService;
    @Resource
    private IUserService userService;

    @PostMapping("/admin/insert")
    public Object insertAdmin(InsertAdminParams params){
        if (adminService.selAdminByMobile(params.getAdminMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号已注册", "");
        }
        adminService.insertAdmin(params);
        return ResponseResult.success();
    }

    @PostMapping("/admin/company/list")
    public Object adminGetCompanyList(AdminSelectCompanyParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(companyService.adminGetCompanyList(params));
    }

    @PostMapping("/admin/user/list")
    public Object adminGetAllUser(String keywords, String userState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(userService.adminGetAllUser(keywords, userState));
    }

    @PostMapping("/admin/company/state")
    public Object adminChangeCompanyState(String companyBoss, String companyId, ECompanyState toBeState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        companyService.changeCompanyState(companyBoss, companyId, toBeState);
        return ResponseResult.success();
    }
}
