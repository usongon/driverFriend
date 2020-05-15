package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.entity.Goods;
import top.zdhunter.driverFriend.bean.param.GoodsChangeParams;
import top.zdhunter.driverFriend.bean.param.GoodsInsertParams;
import top.zdhunter.driverFriend.bean.param.GoodsListQueryParams;
import top.zdhunter.driverFriend.bean.result.GoodsDetailResult;
import top.zdhunter.driverFriend.bean.result.GoodsListResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020/4/29
 */
public interface GoodsService {


    int insert(Goods record);

    int insertSelective(GoodsInsertParams params);

    int updateByGoodsIdAndGoodsState(GoodsChangeParams params);

    int updateGoodsStateByGoodsId(String updatedGoodsState, String goodsId);

    GoodsDetailResult getGoodsResultByGoodsId(String goodsId);

    List<GoodsListResult> getGoodsList(GoodsListQueryParams params);
}

