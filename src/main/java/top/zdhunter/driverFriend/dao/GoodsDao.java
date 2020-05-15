package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zdhunter.driverFriend.bean.entity.Goods;

/**
 *
 * @author zhangdehua
 * @date 2020/4/29
 */
@Mapper
public interface GoodsDao {
    int insert(Goods record);

    int insertSelective(Goods record);

    int updateByGoodsIdAndGoodsState(@Param("updated") Goods updated, @Param("goodsId") String goodsId, @Param("goodsState") String goodsState);

    int updateGoodsStateByGoodsId(@Param("updatedGoodsState") String updatedGoodsState, @Param("goodsId") String goodsId);


}