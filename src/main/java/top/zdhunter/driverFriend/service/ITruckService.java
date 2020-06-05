package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.TruckStateEntity;
import top.zdhunter.driverFriend.bean.param.TruckParams;
import top.zdhunter.driverFriend.bean.result.TruckResult;
import top.zdhunter.driverFriend.enums.ETruckState;

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
