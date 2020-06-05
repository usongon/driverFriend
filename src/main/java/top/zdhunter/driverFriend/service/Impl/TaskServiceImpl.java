package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.TaskEntity;
import top.zdhunter.driverFriend.bean.param.ChangeTaskParam;
import top.zdhunter.driverFriend.bean.param.TaskListQueryParams;
import top.zdhunter.driverFriend.bean.param.TaskParams;
import top.zdhunter.driverFriend.bean.result.TaskListResult;
import top.zdhunter.driverFriend.bean.result.TaskResult;
import top.zdhunter.driverFriend.common.helper.ParamsHelper;
import top.zdhunter.driverFriend.common.utils.DateUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.TaskDao;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.enums.ETaskState;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.ITaskService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-11
 */
@Service
public class TaskServiceImpl implements ITaskService {
    @Resource
    private TaskDao taskDao;
    @Override
    public void addTask(TaskParams params, String issueId) {
        if (DateUtil.parserLocalDateTime(params.getTaskDeadline()).compareTo(LocalDateTime.now()) <= 0){
            throw new BusinessException(EResponseCode.BizError, "结束时间不能早于现在的时间", "");
        }
        TaskEntity entity = new TaskEntity();
        BeanUtils.copyProperties(params, entity);
        entity.setIssueId(issueId);
        entity.setTaskId(UuidUtil.randomUUID());
        entity.setTaskDeadline(DateUtil.parserLocalDateTime(params.getTaskDeadline()));
        taskDao.addTask(entity);
    }

    @Override
    public void changeTask(ChangeTaskParam param) {
        taskDao.changeTask(param);
    }

    @Override
    public void changeTaskState(String taskId, ETaskState toBeState) {
        taskDao.changeTaskState(taskId, toBeState.toString());
    }

    @Override
    public TaskResult getTaskById(String taskId) {
        return taskDao.getTaskById(taskId);
    }

    @Override
    public List<TaskListResult> getTaskList(TaskListQueryParams params) {
        params.setIssueId(ParamsHelper.processStrSearchParams(params.getIssueId()));
        params.setCompanyId(ParamsHelper.processStrSearchParams(params.getCompanyId()));
        params.setDestinationCity(ParamsHelper.processStrSearchParams(params.getDestinationCity()));
        params.setTaskState(ParamsHelper.processStrSearchParams(params.getTaskState()));
        return taskDao.getTaskList(params);
    }

    @Override
    public List<TaskResult> getTaskListByDriver(String driverId) {
        return taskDao.getTaskListByDriver(driverId);
    }
}
