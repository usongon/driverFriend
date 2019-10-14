package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zdhunter.driverFriend.enums.EUserRole;

/**
 * @author zhangdehua
 * @date 2019-10-15
 */
@Mapper
public interface UserDao {

    @Insert("insert into user(user_id, user_name, user_mobile, user_password, user_role) values (#{userId} , #{userName} ," +
            "#{userMobile} , #{userPassword} , #{userRole.name} )")
    void registerUser(@Param("userId") String userId,
                      @Param("userName") String userName,
                      @Param("userMobile") String userMobile,
                      @Param("userPassword") String userPassword,
                      @Param("userRole")EUserRole userRole);
}
