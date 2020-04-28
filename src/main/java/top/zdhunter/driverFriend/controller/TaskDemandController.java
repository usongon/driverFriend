package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.TaskDemandService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
@RestController
@Authorize
public class TaskDemandController {
    @Resource
    private TaskDemandService taskDemandService;

    @PostMapping("task/demand/add")
    public Object addTaskDemand(InsertTaskDemandParams params){
        UserSession session = GlobalHelper.get();
        if (session.getRole().equals(EUserRole.Boss)){
            throw new BusinessException(EResponseCode.BizError, "商家无法使用本模块", "");
        }
        params.setIssueId(session.getUserId());
        params.setIssueType(session.getRole());
        taskDemandService.insertSelective(params);
        return ResponseResult.success();
    }
}
