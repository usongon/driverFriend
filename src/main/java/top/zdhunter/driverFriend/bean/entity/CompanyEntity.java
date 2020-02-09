package top.zdhunter.driverFriend.bean.entity;

import lombok.Data;
import top.zdhunter.driverFriend.enums.ECompanyState;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
@Data
public class CompanyEntity {
    String companyId;
    String companyBoss;
    String companyName;
    String companyMobile;
    String companyCity;
    String companyAddress;
    String companyLogo;
}
