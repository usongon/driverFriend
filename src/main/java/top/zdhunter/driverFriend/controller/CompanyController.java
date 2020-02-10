package top.zdhunter.driverFriend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.bean.param.AdminSelectCompanyParams;
import top.zdhunter.driverFriend.bean.param.CompanyParams;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.ECompanyState;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-02-01
 */
@Authorize
@RestController
public class CompanyController {
    @Resource
    private ICompanyService companyService;
    @Resource
    private IUserService userService;
    @PostMapping("/company/add")
    public Object addCompany(CompanyParams params){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        CompanyEntity entity = new CompanyEntity();
        BeanUtils.copyProperties(params,entity);
        entity.setCompanyBoss(session.getUserId());
        companyService.addCompany(entity);
        return ResponseResult.success();
    }

    @PostMapping("/company/change")
    public Object changeCompany(ChangeCompanyEntity entity){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        companyService.changeCompany(entity, session.getUserId());
        return ResponseResult.success();
    }

    @PostMapping("/company/delete")
    public Object deleteCompany(String companyId){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        companyService.changeCompanyState(session.getUserId(), companyId, ECompanyState.Del);
        return ResponseResult.success();
    }

    @PostMapping("/company/list")
    public Object userGetOwnCompany(AdminSelectCompanyParams params){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        params.setCompanyBoss(session.getUserId());
        return ResponseResult.success(companyService.adminGetCompanyList(params));
    }

}
