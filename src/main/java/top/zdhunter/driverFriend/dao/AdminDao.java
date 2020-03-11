package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.zdhunter.driverFriend.bean.entity.AdminEntity;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Mapper
public interface AdminDao {

    @Insert("insert into admin(admin_id, admin_name, admin_password, admin_mobile) " +
            "values (#{adminId} , #{adminName} , #{adminPassword} , #{adminMobile})")
    void insertAdmin(@Param("adminId") String adminId,
                     @Param("adminName") String adminName,
                     @Param("adminPassword") String adminPassword,
                     @Param("adminMobile") String adminMobile);

    @Select("select * from admin where admin_mobile = #{mobile} and admin_state != 'Del' limit 1")
    AdminEntity selAdminByMobile(@Param("mobile") String mobile);

    @Select("select * from admin where admin_mobile = #{mobile} and admin_password = #{password} and admin_state != 'Del' limit 1")
    AdminEntity selAdminByMobileAndPsw(@Param("mobile") String mobile,
                                       @Param("password") String password);

    @Select("select * from admin where admin_id = #{adminId}")
    AdminEntity selAdminByAdminId(String adminId);
    
}
