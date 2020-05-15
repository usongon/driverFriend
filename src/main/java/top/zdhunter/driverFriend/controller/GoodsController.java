package top.zdhunter.driverFriend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zdhunter.driverFriend.bean.ResponseResult;
import top.zdhunter.driverFriend.bean.param.GoodsChangeParams;
import top.zdhunter.driverFriend.bean.param.GoodsInsertParams;
import top.zdhunter.driverFriend.bean.param.GoodsListQueryParams;
import top.zdhunter.driverFriend.bean.session.UserSession;
import top.zdhunter.driverFriend.common.helper.GlobalHelper;
import top.zdhunter.driverFriend.enums.EResponseCode;
import top.zdhunter.driverFriend.framework.annotation.Authorize;
import top.zdhunter.driverFriend.framework.exception.BusinessException;
import top.zdhunter.driverFriend.service.GoodsService;
import top.zdhunter.driverFriend.service.ICompanyService;
import top.zdhunter.driverFriend.service.IUserService;

import javax.annotation.Resource;

/**
 * @author zhangdehua
 * @date 2020/5/15
 */
@Authorize
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;
    @Resource
    private ICompanyService companyService;
    /**
     * 商家添加商品
     */
    @PostMapping("/boss/goods/add")
    public Object bossAddGoods(GoodsInsertParams params){
        UserSession session = GlobalHelper.get();
        params.setBossId(session.getUserId());
        goodsService.insertSelective(params);
        return ResponseResult.success();
    }

    /**
     * 商家修改商品信息
     */

    @PostMapping("/boss/goods/change")
    public Object bossChangeGoods(GoodsChangeParams params){
        UserSession session = GlobalHelper.get();
        if (!goodsService.getGoodsResultByGoodsId(params.getGoodsId()).getBossId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "你不能修改其他人的商品信息", "");
        }
        goodsService.updateByGoodsIdAndGoodsState(params);
        return ResponseResult.success();
    }

    /**
     * 商家删除商品信息
     */
    @PostMapping("/boss/goods/delete")
    public Object bossDeleteGoods(String goodsId){
        UserSession session = GlobalHelper.get();
        if (!goodsService.getGoodsResultByGoodsId(goodsId).getBossId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "你不能修改其他人的商品信息", "");
        }
        goodsService.updateGoodsStateByGoodsId("Del", goodsId);
        return ResponseResult.success();
    }

    /**
     * 商家获取商品列表
     */
    @PostMapping("/boss/goods/list")
    public Object bossGetGoodsList(GoodsListQueryParams params){
        UserSession session = GlobalHelper.get();
        params.setBossId(session.getUserId());
        params.setBossLocation(companyService.getCompanyDetailByBossId(session.getUserId()).getCompanyCity());
        return ResponseResult.success(goodsService.getGoodsList(params));
    }

    /**
     * 商家获取商品详情
     */
    @PostMapping("/boss/goods/detail")
    public Object bossGetGoodsDetail(String goodsId){
        UserSession session = GlobalHelper.get();
        if (!goodsService.getGoodsResultByGoodsId(goodsId).getBossId().equals(session.getUserId())){
            throw new BusinessException(EResponseCode.BizError, "你只能查看自己发布的商品", "");
        }
        return ResponseResult.success(goodsService.getGoodsResultByGoodsId(goodsId));
    }
}
