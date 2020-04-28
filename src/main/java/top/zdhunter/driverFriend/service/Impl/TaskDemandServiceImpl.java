package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.TaskDemandDao;
import top.zdhunter.driverFriend.service.TaskDemandService;

/**
 * @author zhangdehua
 * @date 2020/4/28
 */
@Service
public class TaskDemandServiceImpl implements TaskDemandService {

    @Resource
    private TaskDemandDao taskDemandDao;

    @Override
    public int insert(TaskDemand record) {
        return taskDemandDao.insert(record);
    }

    @Override
    public int insertSelective(InsertTaskDemandParams params) {
        TaskDemand taskDemand = new TaskDemand();
        BeanUtils.copyProperties(params, taskDemand);
        taskDemand.setDemandId(UuidUtil.randomUUID());
        taskDemand.setIssueType(params.getIssueType().getRole());
        //TODO 货车司机只能输入比自己货车重量小的重量
        return taskDemandDao.insertSelective(taskDemand);
    }

    @Override
    public int updateByDemandIdAndDemandState(TaskDemand updated, String demandId, String demandState) {
        return taskDemandDao.updateByDemandIdAndDemandState(updated, demandId, demandState);
    }

    @Override
    public int updateDemandStateByDemandId(String updatedDemandState, String demandId) {
        return taskDemandDao.updateDemandStateByDemandId(updatedDemandState, demandId);
    }

}

