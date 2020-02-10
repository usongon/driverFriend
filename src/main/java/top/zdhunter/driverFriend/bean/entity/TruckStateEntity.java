package top.zdhunter.driverFriend.bean.entity;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ETruckState;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Data
public class TruckStateEntity {
    String truckId;
    String truckOwner;
    String truckNumber;
    String truckPhoto;
    double maxLaden;
    ETruckState truckState;
}
