package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.TaskEntity;
import top.zdhunter.driverFriend.bean.param.TaskParams;
import top.zdhunter.driverFriend.common.utils.DateUtil;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.TaskDao;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.ITaskService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
}
