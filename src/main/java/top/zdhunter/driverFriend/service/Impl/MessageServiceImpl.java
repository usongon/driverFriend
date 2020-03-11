package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import top.zdhunter.driverFriend.bean.entity.MessageEntity;
import top.zdhunter.driverFriend.bean.entity.MessageTextEntity;
import top.zdhunter.driverFriend.bean.param.MessageInsertParams;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.MessageDao;
import top.zdhunter.driverFriend.dao.MessageTextDao;
import top.zdhunter.driverFriend.service.MessageService;
/**
 *
 * @author zhangdehua
 * @date 2020-03-09
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageDao messageDao;
    @Resource
    private MessageTextDao messageTextDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void send(MessageInsertParams params) {
        String msgTxtId = UuidUtil.randomUUID();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsgId(UuidUtil.randomUUID());
        messageEntity.setMsgTxtId(msgTxtId);
        messageEntity.setSenderId(params.getSenderId());
        messageEntity.setReceiverId(params.getReceiverId());
        //插入message数据库
        messageDao.insertSelective(messageEntity);
        MessageTextEntity messageTextEntity = new MessageTextEntity();
        messageTextEntity.setMsgTxtId(msgTxtId);
        messageTextEntity.setContent(params.getContent());
        messageTextEntity.setAction(params.getAction());
        messageTextEntity.setTarget(params.getTarget());
        //插入messageText数据库
        messageTextDao.insertSelective(messageTextEntity);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return messageDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MessageEntity record) {
        return messageDao.insert(record);
    }

    @Override
    public int insertSelective(MessageEntity record) {
        return messageDao.insertSelective(record);
    }

    @Override
    public MessageEntity selectByPrimaryKey(Long id) {
        return messageDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MessageEntity record) {
        return messageDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MessageEntity record) {
        return messageDao.updateByPrimaryKey(record);
    }

}
