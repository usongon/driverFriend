package com.usongon.driverFriend.service;

import com.usongon.driverFriend.bean.result.BossOverviewResult;

/**
 * @author zhangdehua
 * @date 2020/5/17
 */
public interface BossOverviewService {
    BossOverviewResult getBossOverview(String bossId);
}
