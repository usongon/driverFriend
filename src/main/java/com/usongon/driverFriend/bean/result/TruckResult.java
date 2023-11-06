package com.usongon.driverFriend.bean.result;

import com.usongon.driverFriend.enums.ETruckState;
import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Data
public class TruckResult {
    String truckId;
    String truckOwner;
    String truckOwnerName;
    String truckNumber;
    String truckPhoto;
    double maxLaden;
    ETruckState truckState;
}
