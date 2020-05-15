package top.zdhunter.driverFriend.service.Impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.zdhunter.driverFriend.bean.entity.Goods;
import top.zdhunter.driverFriend.dao.GoodsDao;
import top.zdhunter.driverFriend.service.GoodsService;
/**
 *
 * @author zhangdehua
 * @date 2020/4/29
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Resource
    private GoodsDao goodsDao;

    @Override
    public int insert(Goods record) {
        return goodsDao.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsDao.insertSelective(record);
    }

    @Override
    public int updateByGoodsIdAndGoodsState(Goods updated,String goodsId,String goodsState) {
        return goodsDao.updateByGoodsIdAndGoodsState(updated,goodsId,goodsState);
    }

    @Override
    public int updateGoodsStateByGoodsId(String updatedGoodsState,String goodsId) {
        return goodsDao.updateGoodsStateByGoodsId(updatedGoodsState,goodsId);
    }

}
