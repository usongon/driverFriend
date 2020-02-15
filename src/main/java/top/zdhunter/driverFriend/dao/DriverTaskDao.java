package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.*;
import top.zdhunter.driverFriend.bean.result.DriverTaskResult;

/**
 * @author zhangdehua
 * @date 2020-02-15
 */
@Mapper
public interface DriverTaskDao {

    @Insert("insert into driver_task(task_id, driver_id, truck_id) values (#{taskId}, #{driverId}, #{truckId})")
    void driverGetTask(@Param("taskId") String taskId,
                       @Param("driverId") String driverId,
                       @Param("truckId") String truckId);

    @Update("update driver_task set is_del = 1 where task_id = #{taskId} and driver_id = #{driverId}")
    void driverCancelTask(@Param("taskId") String taskId,
                          @Param("driverId") String driverId);

    @Select("select * from driver_task where task_id = #{taskId} and is_del = 0")
    DriverTaskResult getDriverTaskByTaskId(String taskId);
}
