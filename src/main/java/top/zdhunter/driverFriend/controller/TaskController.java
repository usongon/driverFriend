package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.ChangeTaskParam;
import top.zdhunter.driverFriend.bean.param.TaskListQueryParams;
import top.zdhunter.driverFriend.bean.param.TaskParams;
import top.zdhunter.driverFriend.bean.result.TaskResult;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.ETaskState;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.ITaskService;
import top.zdhunter.driverFriend.service.IUserService;

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
    @Resource
    private IUserService userService;

    @PostMapping("/task/add")
    public Object addTask(TaskParams params){
        UserSession session = GlobalHelper.get();
        params.setCompanyId(companyService.getCompanyDetailByBossId(session.getUserId()).getCompanyId());
        taskService.addTask(params, session.getUserId());
        return ResponseResult.success();
    }

    @PostMapping("/task/change")
    public Object changeTask(ChangeTaskParam param){
        UserSession session = GlobalHelper.get();
        if (!taskService.getTaskById(param.getTaskId()).getIssueId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "操作有误,您没有权限对本任务进行编辑", "");
        }
        if (!taskService.getTaskById(param.getTaskId()).getTaskState().equals(ETaskState.Wait)){
            throw new BusinessException(EResponseCode.BizError, "该任务已不可编辑，请联系领取任务的司机取消", "");
        }
        taskService.changeTask(param);
        return ResponseResult.success();
    }

    @PostMapping("/task/state")
    public Object changeTaskState(String taskId, ETaskState toBeState){
        UserSession session = GlobalHelper.get();
        TaskResult result = taskService.getTaskById(taskId);
        if (!result.getIssueId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "操作有误,您没有权限修改本任务状态", "");
        }
        if (toBeState.equals(ETaskState.Got) || toBeState.equals(ETaskState.Transiting)){
            throw new BusinessException(EResponseCode.BizError, "你不能进行该操作", "");
        }
        if (toBeState.equals(ETaskState.Finished) && !result.getTaskState().equals(ETaskState.Transiting)){
            throw new BusinessException(EResponseCode.BizError, "现在还不能结束该任务", "");
        }
        if ((toBeState.equals(ETaskState.Del)||toBeState.equals(ETaskState.Canceled)) && !result.getTaskState().equals(ETaskState.Wait)){
            throw new BusinessException(EResponseCode.BizError, "当前任务无法进行此操作", "");
        }
        taskService.changeTaskState(taskId, toBeState);
        return ResponseResult.success();
    }

    /**
     * 获取任务详情,不设权限,所有人都可以查看任务详情   需要登录
     * @param taskId 任务Id
     * @return
     */
    @PostMapping("/task/detail")
    public Object getTaskDetailById(String taskId){
        return ResponseResult.success(taskService.getTaskById(taskId));
    }

    @PostMapping("/task/list")
    public Object getTaskList(TaskListQueryParams params){
        UserSession session = GlobalHelper.get();
        //如果是Boss，那就只能查询自己发布的任务列表
        if (userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Boss)) {
            params.setIssueId(session.getUserId());
        }
        return ResponseResult.success(taskService.getTaskList(params));
    }
    @PostMapping("/driver/task/list")
    public Object driverGetTaskList(TaskListQueryParams params){
        UserSession session = GlobalHelper.get();
        return ResponseResult.success(taskService.getTaskList(params));
    }


}
