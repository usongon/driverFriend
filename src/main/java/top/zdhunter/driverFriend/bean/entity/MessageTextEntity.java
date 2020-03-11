package top.zdhunter.driverFriend.bean.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020-03-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTextEntity {
    /**
    * id
    */
    private Long id;

    /**
    * 信内容id
    */
    private String msgTxtId;

    /**
    * 信件详情
    */
    private String content;

    /**
    * 0-无动作
    */
    private Integer action;

    /**
    * 动作目标
    */
    private String target;

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