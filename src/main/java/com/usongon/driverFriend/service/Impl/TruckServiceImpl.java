package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.bean.param.ChangeTruckParams;
import com.usongon.driverFriend.bean.param.TruckParams;
import com.usongon.driverFriend.dao.TruckDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.entity.TruckEntity;
import com.usongon.driverFriend.bean.entity.TruckStateEntity;
import com.usongon.driverFriend.bean.result.TruckResult;
import com.usongon.driverFriend.common.helper.ParamsHelper;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.enums.ETruckState;
import com.usongon.driverFriend.service.ITruckService;

import jakarta.annotation.Resource;
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

    @Override
    public TruckResult getTruckDetailByDriverId(String driverId) {
        return truckDao.getTruckDetailByDriverId(driverId);
    }
}
