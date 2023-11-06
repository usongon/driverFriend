package com.usongon.driverFriend.dao;

import com.usongon.driverFriend.bean.param.GoodsListQueryParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.usongon.driverFriend.bean.entity.Goods;
import com.usongon.driverFriend.bean.result.GoodsDetailResult;
import com.usongon.driverFriend.bean.result.GoodsListResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020/4/29
 */
@Mapper
public interface GoodsDao {
    int insert(Goods record);

    int insertSelective(Goods record);

    int updateByGoodsIdAndGoodsState(@Param("updated") Goods updated, @Param("goodsId") String goodsId);

    int updateGoodsStateByGoodsId(@Param("updatedGoodsState") String updatedGoodsState, @Param("goodsId") String goodsId);

    GoodsDetailResult getGoodsResultByGoodsId(@Param("GoodsId") String GoodsId);

    List<GoodsListResult> getGoodsList(GoodsListQueryParams params);
}