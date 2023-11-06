package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.param.MessageInsertParams;
import com.usongon.driverFriend.bean.entity.MessageEntity;

/**
 *
 * @author zhangdehua
 * @date 2020-03-09
 */
public interface MessageService{

    void send(MessageInsertParams params);

    int deleteByPrimaryKey(Long id);

    int insert(MessageEntity record);

    int insertSelective(MessageEntity record);

    MessageEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageEntity record);

    int updateByPrimaryKey(MessageEntity record);

}
