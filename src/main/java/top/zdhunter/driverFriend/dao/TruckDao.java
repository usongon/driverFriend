package top.zdhunter.driverFriend.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.zdhunter.driverFriend.bean.entity.TruckEntity;
import top.zdhunter.driverFriend.bean.entity.TruckStateEntity;
import top.zdhunter.driverFriend.bean.param.ChangeTruckParams;
import top.zdhunter.driverFriend.bean.param.TruckParams;

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

}
