package com.usongon.driverFriend.bean.param;

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
public class GoodsListQueryParams {
    /**
    * 发布人的地址 市-省
    */
    private String bossLocation;

    /**
    * 商品名
    */
    private String goodsName;

    /**
     * 老板id
     */
    private String bossId;
}