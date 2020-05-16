package top.zdhunter.driverFriend.bean.result;

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
public class GoodsDetailResult {
    /**
    * 商品id
    */
    private String goodsId;

    /**
    * 发布人id
    */
    private String bossId;

    /**
     * 发布人姓名
     */
    private String bossName;

    /**
     * 发布人手机号
     */
    private String bossMobile;

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
    private double goodsWeight;

    /**
    * 商品价格
    */
    private double goodsPrice;

    /**
    * 商品描述  限制100字
    */
    private String goodsDesc;

    private String goodsState;

    /**
    * 发布时间
    */
    private Date createTime;

}