package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.bean.param.AdminSelectCompanyParams;
import com.usongon.driverFriend.bean.param.TaskListQueryParams;
import com.usongon.driverFriend.service.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.param.InsertAdminParams;
import com.usongon.driverFriend.bean.session.AdminSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.ECompanyState;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.ETaskState;
import com.usongon.driverFriend.enums.ETruckState;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2019-11-11
 */
@Authorize
@RestController
public class AdminController {
    @Resource
    private IAdminService adminService;
    @Resource
    private ICompanyService companyService;
    @Resource
    private IUserService userService;
    @Resource
    private ITruckService truckService;
    @Resource
    private ITaskService taskService;

    @PostMapping("/admin/insert")
    public Object insertAdmin(InsertAdminParams params){
        if (adminService.selAdminByMobile(params.getAdminMobile()) != null){
            throw new BusinessException(EResponseCode.BizError, "手机号已注册", "");
        }
        adminService.insertAdmin(params);
        return ResponseResult.success();
    }

    @PostMapping("/admin/company/list")
    public Object adminGetCompanyList(AdminSelectCompanyParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(companyService.adminGetCompanyList(params));
    }

    @PostMapping("/admin/user/list")
    public Object adminGetAllUser(String keywords, String userState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(userService.adminGetAllUser(keywords, userState));
    }

    @PostMapping("/admin/company/state")
    public Object adminChangeCompanyState(String companyBoss, String companyId, ECompanyState toBeState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        companyService.changeCompanyState(companyBoss, companyId, toBeState);
        return ResponseResult.success();
    }

    @PostMapping("/admin/truck/state")
    public Object adminChangeTruckState(String truckId, ETruckState toBeState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        if (truckService.getTruckById(truckId).getTruckState().equals(ETruckState.Del)){
            throw new BusinessException(EResponseCode.BizError, "本辆卡车已删除无法变更状态，请联系数据库管理员", "");
        }
        truckService.changeTruckState(truckId, toBeState);
        return ResponseResult.success();
    }

    @PostMapping("/admin/truck/list")
    public Object adminGetTruckList(String truckOwner, String truckNumber, String truckState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(truckService.getTruckList(truckOwner, truckNumber, truckState));
    }

    @PostMapping("admin/task/list")
    public Object adminGetTaskList(TaskListQueryParams params){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        return ResponseResult.success(taskService.getTaskList(params));
    }

    @PostMapping("/admin/task/state")
    public Object adminChangeTaskState(String taskId, ETaskState toBeState){
        AdminSession session = GlobalHelper.get();
        if (adminService.selAdminByAdminId(session.getAdminId()) == null){
            throw new BusinessException(EResponseCode.BizError, "你不是管理员， 不能使用本模块", "");
        }
        if (taskService.getTaskById(taskId).getTaskState().equals(ETaskState.Del)){
            throw new BusinessException(EResponseCode.BizError, "本任务已删除无法变更状态，请联系数据库管理员", "");
        }
        taskService.changeTaskState(taskId, toBeState);
        return ResponseResult.success();
    }
}
