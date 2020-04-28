package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
public interface TaskDemandService {


    int insert(TaskDemand record);

    int insertSelective(InsertTaskDemandParams params);

    int updateByDemandIdAndDemandState(TaskDemand updated, String demandId, String demandState);

    int updateDemandStateByDemandId(String updatedDemandState, String demandId);

}

