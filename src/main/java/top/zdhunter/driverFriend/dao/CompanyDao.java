package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;

/**
 * @author zhangdehua
 * @date 2020-01-19
 */
@Mapper
public interface CompanyDao {

    /**
     * 插入公司信息
     * @param entity
     */
    @Insert("insert into company(company_id, company_boss, company_name, company_mobile, company_city, company_address, company_logo)" +
            "values (#{companyId} , #{companyBoss}, #{companyName}, #{companyMobile}, #{companyCity}, #{companyAddress}, #{companyLogo})")
    void addCompany(CompanyEntity entity);

    /**
     * 修改公司信息，验证BossId，修改之后变为未审核状态
     * Question  未审核的公司无法显示
     * @param entity
     */
    @Update("update company set company_name = #{companyName}, company_mobile = #{companyMobile}, company_city = #{companyCity}, company_address = #{companyAddress}, " +
            "company_logo = #{companyLogo}, company_state = 'Unaudited' where company_boss = #{companyBoss}")
    void changeCompany(ChangeCompanyEntity entity);
}
