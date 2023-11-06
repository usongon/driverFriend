package com.usongon.driverFriend.bean.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020-03-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {
    /**
    * id
    */
    private Long id;

    /**
    * 消息id
    */
    private String msgId;

    /**
    * 发送人ID
    */
    private String senderId;

    /**
    * 接收人ID
    */
    private String receiverId;

    /**
    * 信内容id
    */
    private String msgTxtId;

    /**
    * 0-未读 1-已读
    */
    private Integer status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    /**
    * 是否已删除 0-未删除 1-已删除
    */
    private Integer isDel;
}