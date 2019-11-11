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

    @Select("select * from admin where mobile = #{mobile} and state != 'Del' limit 1")
    AdminEntity selAdminByMobile(@Param("mobile") String mobile);
}
