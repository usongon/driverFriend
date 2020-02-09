package top.zdhunter.driverFriend.bean.param;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ECompanyState;

/**
 * @author zhangdehua
 * @date 2020-02-09
 */
@Data
public class AdminSelectCompanyParams {
    String companyBoss;
    String companyName;
    String companyMobile;
    String companyCity;
    String companyAddress;
    String companyState;
}
