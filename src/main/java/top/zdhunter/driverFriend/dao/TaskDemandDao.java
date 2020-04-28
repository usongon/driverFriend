package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.result.TaskDemandListResult;
import top.zdhunter.driverFriend.bean.result.TaskDemandResult;

import java.util.List;

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

    /**
     * 获取需求列表
     * @param issueType
     * @param demandStart
     * @param demandDestination
     * @param issueId
     * @return
     */
    List<TaskDemandListResult> getDemandList(@Param("issueType") String issueType,
                                            @Param("demandStart") String demandStart,
                                            @Param("demandDestination") String demandDestination,
                                            @Param("issueId") String issueId);
}