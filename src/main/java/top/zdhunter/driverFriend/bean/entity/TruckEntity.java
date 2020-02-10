package top.zdhunter.driverFriend.bean.entity;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Data
public class TruckEntity {
    String truckId;
    String truckOwner;
    String truckNumber;
    String truckPhoto;
    double maxLaden;
}
