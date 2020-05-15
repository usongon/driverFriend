package top.zdhunter.driverFriend.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import top.zdhunter.driverFriend.bean.entity.Goods;
import top.zdhunter.driverFriend.bean.param.GoodsChangeParams;
import top.zdhunter.driverFriend.bean.param.GoodsInsertParams;
import top.zdhunter.driverFriend.bean.param.GoodsListQueryParams;
import top.zdhunter.driverFriend.bean.result.GoodsDetailResult;
import top.zdhunter.driverFriend.bean.result.GoodsListResult;
import top.zdhunter.driverFriend.common.utils.UuidUtil;
import top.zdhunter.driverFriend.dao.GoodsDao;
import top.zdhunter.driverFriend.service.GoodsService;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.IUserService;

import java.util.List;

/**
 *
 * @author zhangdehua
 * @date 2020/4/29
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private IUserService userService;
    @Resource
    private ICompanyService companyService;

    @Override
    public int insert(Goods record) {
        return goodsDao.insert(record);
    }

    @Override
    public int insertSelective(GoodsInsertParams params) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(params, goods);
        goods.setGoodsId(UuidUtil.randomUUID());
        goods.setBossLocation(companyService.getCompanyDetailByBossId(params.getBossId()).getCompanyCity());
        return goodsDao.insertSelective(goods);
    }

    @Override
    public int updateByGoodsIdAndGoodsState(GoodsChangeParams params) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(params, goods);
        return goodsDao.updateByGoodsIdAndGoodsState(goods, params.getGoodsId());
    }

    @Override
    public int updateGoodsStateByGoodsId(String updatedGoodsState,String goodsId) {
        return goodsDao.updateGoodsStateByGoodsId(updatedGoodsState,goodsId);
    }

    @Override
    public GoodsDetailResult getGoodsResultByGoodsId(String goodsId) {
        return goodsDao.getGoodsResultByGoodsId(goodsId);
    }

    @Override
    public List<GoodsListResult> getGoodsList(GoodsListQueryParams params) {
        return goodsDao.getGoodsList(params);
    }

}
