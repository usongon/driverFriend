package com.usongon.driverFriend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangdehua
 * @date 2020/5/15
 */
@Mapper
public interface BossOverviewDao {

    @Select("select count(*) from task where issue_id = #{bossId} and task_state != 'Del'")
    int getSumIssuedTasks(String bossId);
    @Select("select count(*) from goods where boss_id = #{bossId} and goods_state != 'Del'")
    int getSumIssuedGoods(String bossId);
}
