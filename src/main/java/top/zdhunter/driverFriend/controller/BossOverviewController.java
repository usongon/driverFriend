package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.BossOverviewService;

import javax.annotation.Resource;

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
