package top.zdhunter.driverFriend.bean.result;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ETruckState;

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
