package top.zdhunter.driverFriend.bean.param;

import lombok.Data;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Data
public class ChangeTruckParams {
    String truckId;
    String truckNumber;
    String truckPhoto;
    double maxLaden;
}
