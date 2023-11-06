package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import com.usongon.driverFriend.service.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.ETaskState;
import com.usongon.driverFriend.enums.EUserRole;


import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-02-15
 */
@RestController
@Authorize
public class DriverController {
    @Resource
    private ITaskService taskService;
    @Resource
    private IUserService userService;
    @Resource
    private IDriverTaskService driverTaskService;
    @Resource
    private ITruckService truckService;
    @Resource
    private IDriverOverviewService driverOverviewService;
    @PostMapping("/driver/gettask")
    @Transactional
    public Object driverGetTask(String taskId){
        UserSession session = GlobalHelper.get();
        String truckId = truckService.getTruckDetailByDriverId(session.getUserId()).getTruckId();
        if (truckService.getTruckById(truckId).getMaxLaden() < taskService.getTaskById(taskId).getCargoWeight()){
            throw new BusinessException(EResponseCode.BizError, "货物重量超过货车最大承载量", "");
        }
        if (!taskService.getTaskById(taskId).getTaskState().equals(ETaskState.Wait)){
            throw new BusinessException(EResponseCode.BizError, "该任务无法领取", "");
        }
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "只有司机可以领取任务", "");
        }
        if (taskService.getTaskListByDriver(session.getUserId()) != null){
            throw new BusinessException(EResponseCode.BizError, "你身上有正在执行的任务，请勿分心！", "");
        }
        driverTaskService.driverGetTask(taskId, session.getUserId(), truckId);
        taskService.changeTaskState(taskId, ETaskState.Got);
        return ResponseResult.success();
    }

    @PostMapping("/driver/canceltask")
    @Transactional
    public Object driverCancelTask(String taskId){
        UserSession session = GlobalHelper.get();
        if (!taskService.getTaskById(taskId).getTaskState().equals(ETaskState.Got)){
            throw new BusinessException(EResponseCode.BizError, "该任务无法取消", "");
        }
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "只有司机可以取消任务", "");
        }
        driverTaskService.driverCancelTask(taskId, session.getUserId());
        taskService.changeTaskState(taskId, ETaskState.Wait);
        return ResponseResult.success();
    }

    @PostMapping("/driver/totransit")
    public Object driverTransiting(String taskId){
        UserSession session = GlobalHelper.get();
        if (!taskService.getTaskById(taskId).getTaskState().equals(ETaskState.Got)){
            throw new BusinessException(EResponseCode.BizError, "该任务无法变更为运送中", "");
        }
        if (!driverTaskService.getDriverTaskByTaskId(taskId).getDriverId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "这不是属于你的任务", "");
        }
        taskService.changeTaskState(taskId, ETaskState.Transiting);
        return ResponseResult.success();
    }

    @PostMapping("/driver/overview")
    public Object overview(){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "只有司机可以查看本界面", "");
        }
        return ResponseResult.success(driverOverviewService.getDriverOverviewMsg(session.getUserId()));
    }

}
