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
     * 司机主页的一些信息
     * @param driverId
     * @return
     */
    @Select("select u.user_name as driver_name, tr.truck_number, t.task_state, dt.task_id " +
            "from user u, truck tr, driver_task dt, task t " +
            "where u.user_id = #{driverId}  and u.user_id = tr.truck_owner " +
            "and dt.driver_id = u.user_id and tr.truck_id = dt.truck_id " +
            "and dt.task_id = t.task_id")
    DriverOverviewResult getDriverOverview(@Param("driverId") String driverId);
}
