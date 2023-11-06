package com.usongon.driverFriend.bean.entity;

import com.usongon.driverFriend.enums.ETruckState;
import lombok.Data;

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
