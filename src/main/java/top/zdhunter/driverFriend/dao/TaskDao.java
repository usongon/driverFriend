package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.zdhunter.driverFriend.bean.entity.TaskEntity;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Mapper
public interface TaskDao {
    @Insert("insert into task(task_id, issue_id, company_id, cargo_kind, cargo_weight, task_deadline, destination_city, destination_address, remark) " +
            "values(#{taskId}, #{issueId}, #{companyId}, #{cargoKind}, #{cargoWeight}, #{taskDeadline}, #{destinationCity}, #{destinationAddress}, #{remark})")
    void addTask(TaskEntity entity);
}
