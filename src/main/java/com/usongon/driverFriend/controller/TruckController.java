package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.bean.param.TruckParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.enums.ETruckState;
import com.usongon.driverFriend.enums.EUserRole;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import com.usongon.driverFriend.service.ITruckService;
import com.usongon.driverFriend.service.IUserService;

import jakarta.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@RestController
@Authorize
public class TruckController {
    @Resource
    private ITruckService truckService;
    @Resource
    private IUserService userService;

    @PostMapping("/truck/add")
    public Object addTruck(TruckParams params){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        truckService.addTruck(params, session.getUserId());
        return ResponseResult.success();
    }

    @PostMapping("/truck/change")
    public Object changeTruck(TruckParams params, String truckId){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        truckService.changeTruck(params, truckId);
        return ResponseResult.success();
    }

    /**
     * 可以通过本接口进行删除、启用停用，但是不可以操作未审核的卡车
     * @param truckId
     * @return
     */
    @PostMapping("/truck/changestate")
    public Object deleteTruck(String truckId, ETruckState toBeState){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        if (truckService.getTruckById(truckId).getTruckState().equals(ETruckState.Unaudited)){
            throw new BusinessException(EResponseCode.BizError, "请等待审核", "");
        }
        if (truckService.getTruckById(truckId).getTruckState().equals(ETruckState.Del)){
            throw new BusinessException(EResponseCode.BizError, "错误的操作", "");
        }
        truckService.changeTruckState(truckId, toBeState);
        return ResponseResult.success();
    }

    @PostMapping("/truck/list")
    public Object ownerGetTruckList(String truckNumber, String truckState){
        UserSession session = GlobalHelper.get();
        if (!userService.selUserById(session.getUserId()).getUserRole().equals(EUserRole.Driver)){
            throw new BusinessException(EResponseCode.BizError, "您没有权限", "");
        }
        return ResponseResult.success(truckService.getTruckList(session.getUserId(), truckNumber, truckState));
    }
}
