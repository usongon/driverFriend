package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.zdhunter.driverFriend.bean.entity.TaskEntity;
import top.zdhunter.driverFriend.bean.param.ChangeTaskParam;
import top.zdhunter.driverFriend.bean.param.TaskListQueryParams;
import top.zdhunter.driverFriend.bean.result.TaskListResult;
import top.zdhunter.driverFriend.bean.result.TaskResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Mapper
public interface TaskDao {
    @Insert("insert into task(task_id, issue_id, company_id, cargo_kind, cargo_weight, task_deadline, destination_city, destination_address, remark) " +
            "values(#{taskId}, #{issueId}, #{companyId}, #{cargoKind}, #{cargoWeight}, #{taskDeadline}, #{destinationCity}, #{destinationAddress}, #{remark})")
    void addTask(TaskEntity entity);

    @Update("update task set cargo_kind = #{cargoKind}, cargo_weight = #{cargoWeight}, task_deadline = #{taskDeadline}, " +
            "destination_city = #{destinationCity}, destination_address = #{destinationAddress}, remark = #{remark} " +
            "where task_id = #{taskId}")
    void changeTask(ChangeTaskParam param);

    @Update("update task set task_state = #{toBeState} where task_id = #{taskId}")
    void changeTaskState(String taskId, String toBeState);

    @Select("select t.*, u.user_name as issue_name, c.company_name " +
            "from task t, user u, company c where task_id = #{taskId} " +
            "and t.issue_id = u.user_id and t.company_id = c.company_id " +
            "and t.task_state != 'Del'")
    TaskResult getTaskById(String taskId);

    @Select("<script> " +
            "select t.*, u.user_name as issue_name, c.company_name " +
            "from task t, user u, company c where " +
            "t.issue_id = u.user_id and t.company_id = c.company_id " +
            "and t.task_state != 'Del' " +
            "<if test = 'issueId != null'> and t.issue_id = #{issueId} </if>" +
            "<if test = 'companyId != null'> and t.company_id = #{companyId} </if>" +
            "<if test = 'minWeight != null'> and t.cargo_weight <![CDATA[>=]]> #{minWeight} </if>" +
            "<if test = 'maxWeight != null'> and t.cargo_weight <![CDATA[<=]]> #{maxWeight} </if>" +
            "<if test = 'destinationCity != null'> and t.destination_city = #{destinationCity} </if>" +
            "<if test = 'taskState != null'> and t.task_state = #{taskState} </if>" +
            "</script>")
    List<TaskListResult> getTaskList(TaskListQueryParams params);
}
