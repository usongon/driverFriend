package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.param.GoodsListQueryParams;
import com.usongon.driverFriend.bean.entity.Goods;
import com.usongon.driverFriend.bean.param.GoodsChangeParams;
import com.usongon.driverFriend.bean.param.GoodsInsertParams;
import com.usongon.driverFriend.bean.result.GoodsDetailResult;
import com.usongon.driverFriend.bean.result.GoodsListResult;

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

