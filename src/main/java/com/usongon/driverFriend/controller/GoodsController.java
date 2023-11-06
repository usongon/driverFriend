package com.usongon.driverFriend.controller;

import com.usongon.driverFriend.bean.ResponseResult;
import com.usongon.driverFriend.bean.param.GoodsListQueryParams;
import com.usongon.driverFriend.bean.param.TaskParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usongon.driverFriend.bean.param.GoodsChangeParams;
import com.usongon.driverFriend.bean.param.GoodsInsertParams;
import com.usongon.driverFriend.bean.result.GoodsDetailResult;
import com.usongon.driverFriend.bean.session.AdminSession;
import com.usongon.driverFriend.bean.session.UserSession;
import com.usongon.driverFriend.common.helper.GlobalHelper;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.framework.annotation.Authorize;
import com.usongon.driverFriend.framework.exception.BusinessException;
import com.usongon.driverFriend.service.GoodsService;
import com.usongon.driverFriend.service.ICompanyService;
import com.usongon.driverFriend.service.ITaskService;

import jakarta.annotation.Resource;

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
    @Resource
    private ITaskService taskService;
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

    /**
     * 将已完结的商品信息转为任务
     */

    @PostMapping("/boss/goods/task")
    public Object goodsTransformToTask(String goodsId, String taskDeadline, String destinationCity, String destinationAddress, String remark){
        if (remark == null){
            remark = "无";
        }
        UserSession session = GlobalHelper.get();
        GoodsDetailResult goodsResult = goodsService.getGoodsResultByGoodsId(goodsId);
        TaskParams taskParams = new TaskParams(companyService.getCompanyDetailByBossId(session.getUserId()).getCompanyId(),
                goodsResult.getGoodsName(), (float)goodsResult.getGoodsWeight(), taskDeadline, destinationCity, destinationAddress, remark);
        taskService.addTask(taskParams, session.getUserId());
        return ResponseResult.success();
    }

    /**
     * 超管获取商品列表
     */

    @PostMapping("/admin/goods/list")
    public Object adminGetGoodsList(GoodsListQueryParams params){
        AdminSession session = GlobalHelper.get();
        return ResponseResult.success(goodsService.getGoodsList(params));
    }

    /**
     * 超管修改商品状态
     */
    @PostMapping("/admin/goods/state")
    public Object adminChangeGoodsState(String goodsId, String toBeState){
        AdminSession session = GlobalHelper.get();
        goodsService.updateGoodsStateByGoodsId(toBeState, goodsId);
        return ResponseResult.success();
    }
}
