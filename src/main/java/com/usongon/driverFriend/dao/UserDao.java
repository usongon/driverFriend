package com.usongon.driverFriend.dao;

import org.apache.ibatis.annotations.*;
import com.usongon.driverFriend.bean.entity.UserEntity;
import com.usongon.driverFriend.bean.result.AdminUserResult;
import com.usongon.driverFriend.bean.result.UserResult;
import com.usongon.driverFriend.enums.EUserRole;

import java.util.List;

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
     * @param mobile 手机号
     * @return 用户实体
     */
    @Select("select * from user where user_mobile = #{mobile} and user_state != 'Del' limit 1")
    UserEntity selUserByMobile(@Param("mobile") String mobile);

    /**
     * 根据用户id查找
     * @param userId
     * @return
     */
    @Select("select * from user where user_id = #{userId} and user_state != 'Del' limit 1")
    UserEntity selUserById(@Param("userId") String userId);
    /**
     * 修改用户信息
     * @param userId 要修改的用户id
     * @param userName 要改成的用户名
     * @param userMobile 要改成的手机号
     */
    @Update("update user set user_name = #{userName}, user_mobile = #{userMobile} where user_id = #{userId}")
    void updateUser(@Param("userId") String userId,
                    @Param("userName") String userName,
                    @Param("userMobile") String userMobile);

    /**
     * 修改密码
     * @param userId 要修改密码的用户id
     * @param newPassword 新密码
     */
    @Update("update user set user_password = #{newPassword} where user_id = #{userId}")
    void changePassword(@Param("userId") String userId,
                        @Param("newPassword") String newPassword);

    @Select("<script>" +
            "select * from user where user_state != 'Del'" +
            "<if test = 'userState != null'> and user_state = #{userState} </if>" +
            "<if test = 'keywords != null'> and (" +
            "user_name like concat(concat('%',#{keywords}),'%') or " +
            "user_mobile like concat(concat('%',#{keywords}),'%') )</if>" +
            "</script>")
    List<AdminUserResult> adminGetAllUser(@Param("keywords") String keywords,
                                          @Param("userState") String userState);

    @Select("select * from user where user_id=#{userId} and user_state != 'Del'")
    UserResult getUserDetail(String userId);
}
