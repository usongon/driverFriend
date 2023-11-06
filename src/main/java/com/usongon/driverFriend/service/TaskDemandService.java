package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.entity.TaskDemand;
import com.usongon.driverFriend.bean.param.DemandListQueryParams;
import com.usongon.driverFriend.bean.param.InsertTaskDemandParams;
import com.usongon.driverFriend.bean.result.TaskDemandListResult;
import com.usongon.driverFriend.bean.result.TaskDemandResult;
import com.usongon.driverFriend.enums.EDemandState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
public interface TaskDemandService {


    int insert(TaskDemand record);

    int insertSelective(InsertTaskDemandParams params);

    int updateByDemandIdAndDemandState(InsertTaskDemandParams params);

    int updateDemandStateByDemandId(EDemandState state, String demandId);

    TaskDemandResult getDemandDetail(String demandId);

    List<TaskDemandListResult> getDemandList(DemandListQueryParams params);
}

