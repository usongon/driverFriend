package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.result.TaskDemandResult;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
@Mapper
public interface TaskDemandDao {
    int insert(TaskDemand record);

    int insertSelective(TaskDemand record);

    int updateByDemandIdAndDemandState(@Param("updated") TaskDemand updated, @Param("demandId") String demandId);

    int updateDemandStateByDemandId(@Param("updatedDemandState") String updatedDemandState, @Param("demandId") String demandId);

    TaskDemandResult getDemandDetail(@Param("demandId") String demandId);
}