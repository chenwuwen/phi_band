package cn.kanyun.phi_band.common.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeviceDao {
    @Query("SELECT * FROM device")
    List<Device> getAll();

    @Query("SELECT * FROM Device WHERE id IN (:deviceIds)")
    List<Device> loadAllByIds(int[] deviceIds);

    @Query("SELECT * FROM Device WHERE device_name LIKE :deviceName  LIMIT 1")
    Device findByName(String deviceName);

    @Insert
    void insertAll(Device... Devices);

    @Delete
    void delete(Device Device);
}
