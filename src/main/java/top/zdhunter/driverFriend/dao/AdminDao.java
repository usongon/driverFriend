package top.zdhunter.driverFriend.dao;

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
    @Select("select * from admin where mobile = #{mobile} and state != 'Del' limit 1")
    AdminEntity selAdminByMobile(@Param("mobile") String mobile);
}
