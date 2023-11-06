package com.usongon.driverFriend.dao;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.usongon.driverFriend.bean.entity.MessageTextEntity;

/**
 *
 * @author zhangdehua
 * @date 2020-03-11
 */
@Mapper
public interface MessageTextDao {
    int deleteByPrimaryKey(Long id);

    int insert(MessageTextEntity record);

    int insertSelective(MessageTextEntity record);

    MessageTextEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageTextEntity record);

    int updateByPrimaryKey(MessageTextEntity record);

    /**
     * 查看私信详情
     * @param msgTxtId 私信Id
     * @return 私信详情
     */
    MessageTextEntity selectOneByMsgTxtIdAndIsDel(@Param("msgTxtId")String msgTxtId);


}