package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.zdhunter.driverFriend.bean.entity.TaskDemand;
import top.zdhunter.driverFriend.bean.param.DemandListQueryParams;
import top.zdhunter.driverFriend.bean.param.InsertTaskDemandParams;
import top.zdhunter.driverFriend.bean.result.TaskDemandListResult;
import top.zdhunter.driverFriend.bean.result.TaskDemandResult;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.TaskDemandDao;
import top.zdhunter.driverFriend.enums.EDemandState;
import top.zdhunter.driverFriend.enums.EUserRole;
import top.zdhunter.driverFriend.service.TaskDemandService;

import java.util.List;

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
    public int updateByDemandIdAndDemandState(InsertTaskDemandParams params) {
        TaskDemand updated = new TaskDemand();
        BeanUtils.copyProperties(params, updated);
        return taskDemandDao.updateByDemandIdAndDemandState(updated, updated.getDemandId());
    }

    @Override
    public int updateDemandStateByDemandId(EDemandState state, String demandId) {
        return taskDemandDao.updateDemandStateByDemandId(state.getState(), demandId);
    }

    @Override
    public TaskDemandResult getDemandDetail(String demandId) {
        return taskDemandDao.getDemandDetail(demandId);
    }

    @Override
    public List<TaskDemandListResult> getDemandList(DemandListQueryParams params) {
//        TaskDemandListResult result =  taskDemandDao.getDemandList(params.getIssueType(),
//                params.getDemandStart(), params.getDemandDestination(), params.getIssueId());
//        if (result.getIssueType().equals(EUserRole.Driver)){
//            result.setDemandName("【车找货源】" + result.getDemandName());
//        }else {
//            result.setDemandName("【求购信息】" + result.getDemandName());
//        }
        return taskDemandDao.getDemandList(params.getIssueType(),
                params.getDemandStart(), params.getDemandDestination(), params.getIssueId());
    }
}

