package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.TruckStateEntity;
import top.zdhunter.driverFriend.bean.param.TruckParams;
import top.zdhunter.driverFriend.enums.ETruckState;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
public interface ITruckService {
    void addTruck(TruckParams params, String owner);
    void changeTruck(TruckParams params, String owner);
    void changeTruckState(String truckId, ETruckState toBeState);
    TruckStateEntity getTruckById(String truckId);
}
