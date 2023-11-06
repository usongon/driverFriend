package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.param.TruckParams;
import com.usongon.driverFriend.bean.entity.TruckStateEntity;
import com.usongon.driverFriend.bean.result.TruckResult;
import com.usongon.driverFriend.enums.ETruckState;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
public interface ITruckService {
    void addTruck(TruckParams params, String owner);
    void changeTruck(TruckParams params, String owner);
    void changeTruckState(String truckId, ETruckState toBeState);
    TruckStateEntity getTruckById(String truckId);
    List<TruckResult> getTruckList(String truckOwner, String truckNumber, String truckState);
    TruckResult getTruckDetailByDriverId(String driverId);
}
