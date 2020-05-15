package top.zdhunter.driverFriend.bean.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 商品表
 * @author zhangdehua
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsChangeParams {
    /**
    * 商品id
    */
    private String goodsId;

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