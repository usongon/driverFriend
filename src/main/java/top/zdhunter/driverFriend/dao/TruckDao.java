package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.*;
import top.zdhunter.driverFriend.bean.entity.TruckEntity;
import top.zdhunter.driverFriend.bean.entity.TruckStateEntity;
import top.zdhunter.driverFriend.bean.param.ChangeTruckParams;
import top.zdhunter.driverFriend.bean.param.TruckParams;
import top.zdhunter.driverFriend.bean.result.TruckResult;

import java.util.List;

/**
 * @author zhangdehua
 * @date 2020-02-10
 */
@Mapper
public interface TruckDao {
    /**
     * 插入卡车信息
     * @param entity
     */
    @Insert("insert into truck(truck_id, truck_owner, truck_number, truck_photo, max_laden) " +
            "values(#{truckId}, #{truckOwner}, #{truckNumber}, #{truckPhoto}, #{maxLaden})")
    void addTruck(TruckEntity entity);

    @Update("update truck set truck_number = #{truckNumber}, truck_photo = #{truckPhoto}, max_laden = #{maxLaden}, truck_state = 'Unaudited' where truck_id = #{truckId}")
    void changeTruck(ChangeTruckParams params);

    @Update("update truck set truck_state = #{toBeState} where truck_id = #{truckId}")
    void changeTruckState(String truckId, String toBeState);

    @Select("select * from truck where truck_id = #{truckId}")
    TruckStateEntity getTruckById(String truckId);

    /**
     * 后续可能会加上承重范围的筛选条件
     * @param truckOwner
     * @param truckNumber
     * @param truckState
     * @return
     */
    @Select("<script>" +
            "select t.*, u.user_name as truck_owner_name from truck t, user u where t.truck_state != 'Del' and t.truck_owner = u.user_id " +
            "<if test = 'truckOwner != null'> and t.truck_owner = #{truckOwner} </if>" +
            "<if test = 'truckNumber != null'> and t.truck_number = #{truckNumber} </if>" +
            "<if test = 'truckState != null'> and t.truck_state = #{truckState} </if> " +
            "</script>")
    List<TruckResult> getTruckList(@Param("truckOwner") String truckOwner,
                                   @Param("truckNumber") String truckNumber,
                                   @Param("truckState") String truckState);

}
