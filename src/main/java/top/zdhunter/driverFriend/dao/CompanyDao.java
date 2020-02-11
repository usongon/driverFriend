package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.*;
import top.zdhunter.driverFriend.bean.entity.ChangeCompanyEntity;
import top.zdhunter.driverFriend.bean.entity.CompanyEntity;
import top.zdhunter.driverFriend.bean.result.AdminCompanyResult;

import java.util.List;

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

    /**
     * 通用接口修改状态
     * 删除公司
     * 考虑到实际情况，未审核的公司应该也可以删除(?)
     * @param companyBoss
     */
    @Update("update company set company_state = #{toBeState} where company_boss = #{companyBoss} and company_id = #{companyId}")
    void changeCompanyState(@Param("companyBoss") String companyBoss,
                       @Param("companyId") String companyId,
                       @Param("toBeState") String toBeState);

    @Select("<script>" +
            "select c.*, b.user_name as company_boss_name from company c, user b where c.company_state != 'Del' and c.company_boss = b.user_id " +
            "<if test = 'companyBoss != null'> and c.company_boss = #{companyBoss} </if>" +
            "<if test = 'companyName != null'> and c.company_name = #{companyName} </if>" +
            "<if test = 'companyMobile != null'> and c.company_mobile = #{companyMobile} </if>" +
            "<if test = 'companyCity != null'> and c.company_city = #{companyCity} </if>" +
            "<if test = 'companyAddress != null'> and c.company_address = #{companyAddress} </if>" +
            "<if test = 'companyState != null'> and c.company_state = #{companyState} </if>" +
            "</script>")
    List<AdminCompanyResult> adminSelectCompanyList(@Param("companyBoss") String companyBoss,
                                                    @Param("companyName") String companyName,
                                                    @Param("companyMobile") String companyMobile,
                                                    @Param("companyCity") String companyCity,
                                                    @Param("companyAddress") String companyAddress,
                                                    @Param("companyState") String companyState);

    @Select("select * from company where company_id = #{companyId}")
    CompanyEntity getCompanyById(String companyId);
}
