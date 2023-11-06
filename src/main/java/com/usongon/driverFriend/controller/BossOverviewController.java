package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.EUserRole;
import com.usongon.driverFriend.service.BossOverviewService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020/5/17
 */
@Authorize
@RestController
public class BossOverviewController {
    @Resource
    private BossOverviewService bossOverviewService;

    /**
     * 商家主页信息
     */
    @PostMapping("/boss/overview")
    public Object getBossOverview(){
        UserSession session = GlobalHelper.get();
        if (!session.getRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "你不是商家", "");
        }
        return ResponseResult.success(bossOverviewService.getBossOverview(session.getUserId()));
    }
}
