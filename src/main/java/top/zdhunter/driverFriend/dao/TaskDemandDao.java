package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zdhunter.driverFriend.bean.entity.TaskDemand;

/**
 *
 * @author zhangdehua
 * @date 2020/4/28
 */
@Mapper
public interface TaskDemandDao {
    int insert(TaskDemand record);

    int insertSelective(TaskDemand record);

    int updateByDemandIdAndDemandState(@Param("updated") TaskDemand updated, @Param("demandId") String demandId, @Param("demandState") String demandState);

    int updateDemandStateByDemandId(@Param("updatedDemandState") String updatedDemandState, @Param("demandId") String demandId);
}