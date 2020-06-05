package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.zdhunter.driverFriend.bean.result.DriverOverviewResult;

/**
 * @author zhangdehua
 * @date 2020-03-23
 */
@Mapper
public interface DriverOverviewDao {
    /**
     * 已接受任务数
     * @param driverId
     * @return
     */
    @Select("select count(*) from driver_task dt, task t where driver_id = #{driverId} " +
            "and t.task_id = dt.task_id and t.task_state != 'Del'")
    int sumGetTask(@Param("driverId") String driverId);

    /**
     * 已发布需求数
     * @param driverId
     * @return
     */
    @Select("select count(*) from task_demand where issue_id = #{driverId} and demand_state != 'Del'")
    int sumIssueDemand(String driverId);
}
