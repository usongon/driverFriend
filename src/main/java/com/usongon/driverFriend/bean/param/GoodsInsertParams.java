package com.usongon.driverFriend.bean.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
    * 商品表
 * @author zhangdehua
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInsertParams {
    /**
    * 发布人id
    */
    private String bossId;

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
}