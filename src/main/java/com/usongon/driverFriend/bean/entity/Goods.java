package com.usongon.driverFriend.bean.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zhangdehua
 * @date 2020/4/29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer id;

    /**
    * 商品id
    */
    private String goodsId;

    /**
    * 发布人id
    */
    private String bossId;

    /**
    * 发布人的地址 市-省
    */
    private String bossLocation;

    /**
    * 商品名
    */
    private String goodsName;

    /**
    * 商品重量
    */
    private Double goodsWeight;

    /**
    * 商品价格
    */
    private Double goodsPrice;

    /**
    * 商品描述  限制100字
    */
    private String goodsDesc;

    /**
    * Wait-等待中 Finished-已出售 Del-已删除
    */
    private String goodsState;

    /**
    * 发布时间
    */
    private Date createTime;

    private Date updateTime;
}