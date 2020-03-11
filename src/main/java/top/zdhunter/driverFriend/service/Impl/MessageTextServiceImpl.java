package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.zdhunter.driverFriend.dao.MessageTextDao;
import top.zdhunter.driverFriend.bean.entity.MessageTextEntity;
import top.zdhunter.driverFriend.service.MessageTextService;
/**
 *
 * @author zhangdehua
 * @date 2020-03-11
 */
@Service
public class MessageTextServiceImpl implements MessageTextService{

    @Resource
    private MessageTextDao messageTextDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return messageTextDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MessageTextEntity record) {
        return messageTextDao.insert(record);
    }

    @Override
    public int insertSelective(MessageTextEntity record) {
        return messageTextDao.insertSelective(record);
    }

    @Override
    public MessageTextEntity selectByPrimaryKey(Long id) {
        return messageTextDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MessageTextEntity record) {
        return messageTextDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MessageTextEntity record) {
        return messageTextDao.updateByPrimaryKey(record);
    }

}
