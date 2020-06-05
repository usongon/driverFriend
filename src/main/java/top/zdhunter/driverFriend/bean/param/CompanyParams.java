package top.zdhunter.driverFriend.bean.param;

import lombok.Data;
import lombok.NonNull;
import top.zdhunter.driverFriend.enums.ECompanyState;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
@Data
public class CompanyParams {
     String companyName;
     String companyMobile;
     String companyCity;
     String companyAddress;
     String companyLogo;
}
