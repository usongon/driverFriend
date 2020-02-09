package top.zdhunter.driverFriend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.bean.param.CompanyParams;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.service.ICompanyService;

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
    @PostMapping("/company/add")
    public Object addCompany(CompanyParams params){
        UserSession session = GlobalHelper.get();
        CompanyEntity entity = new CompanyEntity();
        BeanUtils.copyProperties(params,entity);
        entity.setCompanyBoss(session.getUserId());
        companyService.addCompany(entity);
        return ResponseResult.success();
    }

    @PostMapping("/company/change")
    public Object changeCompany(ChangeCompanyEntity entity){
        UserSession session = GlobalHelper.get();
        companyService.changeCompany(entity, session.getUserId());
        return ResponseResult.success();
    }
}