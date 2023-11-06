package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.bean.param.AdminSelectCompanyParams;
import com.usongon.driverFriend.bean.param.CompanyParams;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.entity.ChangeCompanyEntity;
import com.usongon.driverFriend.bean.entity.CompanyEntity;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.ECompanyState;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.EUserRole;
import com.usongon.driverFriend.service.ICompanyService;
import com.usongon.driverFriend.service.IUserService;

import jakarta.annotation.Resource;

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
        if (StringUtils.isEmpty(params.getCompanyName().trim()) ||
                StringUtils.isEmpty(params.getCompanyMobile().trim()) ||
                        StringUtils.isEmpty(params.getCompanyAddress().trim())||
                        StringUtils.isEmpty(params.getCompanyCity().trim())){
            throw new BusinessException(EResponseCode.BizError, "必填数据不能为空", "");
        }
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        if (companyService.getCompanyByBossId(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "你已经有公司了，不能再添加了", "");
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

    /**
     * 查询boss名下是否有公司存在的接口
     */

    @PostMapping("/company/ishave")
    public Object isBossHaveCompany(){
        UserSession session = GlobalHelper.get();
        return ResponseResult.success(companyService.getCompanyByBossId(session.getUserId()));
    }

    /**
     * 获取Boss下的公司的详情
     */

    @PostMapping("/company/detail")
    public Object getCompanyDetail(){
        UserSession session = GlobalHelper.get();
        return ResponseResult.success(companyService.getCompanyDetailByBossId(session.getUserId()));
    }
}
