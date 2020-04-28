package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.DemandListQueryParams;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;
import top.zdhunter.driverFriend.bean.result.TaskDemandResult;
import top.zdhunter.driverFriend.bean.session.AdminSession;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EDemandState;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.IAdminService;
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
    @Resource
    private IAdminService adminService;

    @PostMapping("/task/demand/add")
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

    @PostMapping("/task/demand/update")
    public Object updateTaskDemand(InsertTaskDemandParams params){
        UserSession session = GlobalHelper.get();
        if (taskDemandService.getDemandDetail(params.getDemandId()).getIssueId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "您不能修改其他人的需求", "");
        }
        taskDemandService.updateByDemandIdAndDemandState(params);
        return ResponseResult.success();
    }

    @PostMapping("/admin/demand/state")
    public Object adminChangeDemandState(String demandId, EDemandState state){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员无法操作", "");
        }
        taskDemandService.updateDemandStateByDemandId(state, demandId);
        return ResponseResult.success();
    }

    @PostMapping("/task/demand/state")
    public Object userChangeDemandState(String demandId, EDemandState state){
        UserSession session = GlobalHelper.get();
        if (taskDemandService.getDemandDetail(demandId).getIssueId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "您不能修改其他人的需求状态", "");
        }
        taskDemandService.updateDemandStateByDemandId(state, demandId);
        return ResponseResult.success();
    }

    @PostMapping("/task/demand/detail")
    public Object getDemandDetail(String demandId){
        UserSession session = GlobalHelper.get();
        TaskDemandResult result = taskDemandService.getDemandDetail(demandId);
        if (!session.getRole().equals(EUserRole.Boss) && !result.getIssueId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "你不能查看别人的需求", "");
        }
        return ResponseResult.success(result);
    }

    @PostMapping("/task/demand/list")
    public Object getDemandList(DemandListQueryParams params){
        UserSession session = GlobalHelper.get();
        if (!session.getRole().equals(EUserRole.Boss)){
            params.setIssueId(session.getUserId());
        }
        return ResponseResult.success(taskDemandService.getDemandList(params));
    }
}
