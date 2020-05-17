package top.zdhunter.driverFriend.service;

import top.zdhunter.driverFriend.bean.result.BossOverviewResult;

/**
 * @author zhangdehua
 * @date 2020/5/17
 */
public interface BossOverviewService {
    BossOverviewResult getBossOverview(String bossId);
}
