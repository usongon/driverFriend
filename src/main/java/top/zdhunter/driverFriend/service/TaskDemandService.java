package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.param.DemandListQueryParams;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;
import top.zdhunter.driverFriend.bean.result.TaskDemandListResult;
import top.zdhunter.driverFriend.bean.result.TaskDemandResult;
import top.zdhunter.driverFriend.enums.EDemandState;

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

