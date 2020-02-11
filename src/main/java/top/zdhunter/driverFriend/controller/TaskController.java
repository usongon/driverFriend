package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.TaskParams;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.ITaskService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@RestController
@Authorize
public class TaskController {
    @Resource
    private ITaskService taskService;
    @Resource
    private ICompanyService companyService;

    @PostMapping("/task/add")
    public Object addTask(TaskParams params){
        UserSession session = GlobalHelper.get();
        if (!companyService.getCompanyById(params.getCompanyId()).getCompanyBoss().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "操作有误", "");
        }
        taskService.addTask(params, session.getUserId());
        return ResponseResult.success();
    }
}
