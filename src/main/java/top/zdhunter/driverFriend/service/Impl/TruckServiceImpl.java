package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.zdhunter.driverFriend.bean.entity.TruckEntity;
import top.zdhunter.driverFriend.bean.entity.TruckStateEntity;
import top.zdhunter.driverFriend.bean.param.ChangeTruckParams;
import top.zdhunter.driverFriend.bean.param.TruckParams;
import top.zdhunter.driverFriend.bean.result.TruckResult;
import top.zdhunter.driverFriend.common.helper.ParamsHelper;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.TruckDao;
import top.zdhunter.driverFriend.enums.ETruckState;
import top.zdhunter.driverFriend.service.ITruckService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Service
public class TruckServiceImpl implements ITruckService {
    @Resource
    private TruckDao truckDao;


    @Override
    public void addTruck(TruckParams params, String owner) {
        TruckEntity entity = new TruckEntity();
        BeanUtils.copyProperties(params,entity);
        entity.setTruckId(UuidUtil.randomUUID());
        entity.setTruckOwner(owner);
        truckDao.addTruck(entity);
    }

    @Override
    public void changeTruck(TruckParams params, String owner) {
        ChangeTruckParams changeParams = new ChangeTruckParams();
        BeanUtils.copyProperties(params, changeParams);
        changeParams.setTruckId(owner);
        truckDao.changeTruck(changeParams);
    }

    @Override
    public void changeTruckState(String truckId, ETruckState toBeState) {
        truckDao.changeTruckState(truckId, toBeState.toString());
    }

    @Override
    public TruckStateEntity getTruckById(String truckId) {
        return truckDao.getTruckById(truckId);
    }

    @Override
    public List<TruckResult> getTruckList(String truckOwner, String truckNumber, String truckState) {
        return truckDao.getTruckList(
                ParamsHelper.processStrSearchParams(truckOwner),
                ParamsHelper.processStrSearchParams(truckNumber),
                ParamsHelper.processStrSearchParams(truckState));
    }
}
