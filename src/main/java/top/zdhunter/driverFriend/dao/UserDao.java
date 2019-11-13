package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.*;
import top.zdhunter.driverFriend.bean.entity.UserEntity;
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

    /**
     * 修改user_state  抽象接口 删除、审核、启用、停用 都可以用
     * @param userId 要操作的用户Id
     * @param toBeState user_state要变成什么
     */
    @Update("update user set user_state = #{toBeState} where user_id = #{userId} ")
    void changeUserState(@Param("userId") String userId,
                         @Param("toBeState") String toBeState);

    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    @Select("select * from user where user_mobile = #{mobile} and user_state != 'Del' limit 1")
    UserEntity selUserByMobile(@Param("mobile") String mobile);

}
