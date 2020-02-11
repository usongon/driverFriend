package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.param.TaskParams;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
public interface ITaskService {
    void addTask(TaskParams params, String issueId);
}
