package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.MessageTextEntity;
    /**
 *
 * @author zhangdehua
 * @date 2020-03-11
 */
public interface MessageTextService{


    int deleteByPrimaryKey(Long id);

    int insert(MessageTextEntity record);

    int insertSelective(MessageTextEntity record);

    MessageTextEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageTextEntity record);

    int updateByPrimaryKey(MessageTextEntity record);

}
