package com.usongon.driverFriend.dao;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.usongon.driverFriend.bean.entity.MessageEntity;
import com.usongon.driverFriend.bean.entity.MessageSelEntity;

import java.util.List;

/**
 *
 * @author zhangdehua
 * @date 2020-03-09
 */
@Mapper
public interface MessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(MessageEntity record);

    int insertSelective(MessageEntity record);

    MessageEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageEntity record);

    int updateByPrimaryKey(MessageEntity record);

    Long countByStatusAndReceiverId(@Param("status")Integer status,@Param("receiverId")String receiverId);

    List<MessageSelEntity> selectAllByReceiverIdAndStatusAndIsDelNot(@Param("receiverId")String receiverId, @Param("status")Integer status, @Param("notIsDel")Integer notIsDel);

}