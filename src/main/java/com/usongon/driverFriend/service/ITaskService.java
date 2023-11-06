package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.param.ChangeTaskParam;
import com.usongon.driverFriend.bean.param.TaskListQueryParams;
import com.usongon.driverFriend.bean.param.TaskParams;
import com.usongon.driverFriend.bean.result.TaskListResult;
import com.usongon.driverFriend.bean.result.TaskResult;
import com.usongon.driverFriend.enums.ETaskState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
public interface ITaskService {
    void addTask(TaskParams params, String issueId);
    void changeTask(ChangeTaskParam param);
    void changeTaskState(String taskId, ETaskState toBeState);
    TaskResult getTaskById(String taskId);
    List<TaskListResult> getTaskList(TaskListQueryParams params);
    List<TaskResult> getTaskListByDriver(String driverId);
}
