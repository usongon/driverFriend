package com.usongon.driverFriend.bean.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 * @author zhangdehua
 * @date 2020/4/29
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsListResult {
    /**
    * 商品id
    */
    private String goodsId;

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
     * 商品状态
     */
    private String goodsState;
}