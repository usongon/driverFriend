package com.usongon.driverFriend.service.Impl;

import com.usongon.driverFriend.bean.param.GoodsListQueryParams;
import com.usongon.driverFriend.dao.GoodsDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import com.usongon.driverFriend.bean.entity.Goods;
import com.usongon.driverFriend.bean.param.GoodsChangeParams;
import com.usongon.driverFriend.bean.param.GoodsInsertParams;
import com.usongon.driverFriend.bean.result.GoodsDetailResult;
import com.usongon.driverFriend.bean.result.GoodsListResult;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.service.GoodsService;
import com.usongon.driverFriend.service.ICompanyService;
import com.usongon.driverFriend.service.IUserService;

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
