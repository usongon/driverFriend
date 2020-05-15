package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.Goods;

/**
 * @author zhangdehua
 * @date 2020/4/29
 */
public interface GoodsService {


    int insert(Goods record);

    int insertSelective(Goods record);

    int updateByGoodsIdAndGoodsState(Goods updated, String goodsId, String goodsState);

    int updateGoodsStateByGoodsId(String updatedGoodsState, String goodsId);

}

