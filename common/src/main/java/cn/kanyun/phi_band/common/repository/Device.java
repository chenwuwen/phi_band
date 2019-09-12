package cn.kanyun.phi_band.common.repository;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Data;

/**
 * 默认情况下，Room使用类名作为数据库表名。如果希望表具有不同的名称，请设置@Entity注解的tableName属性
 */
@Entity(tableName = "device")
@Data
public class Device {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * MAC地址
     */
    @ColumnInfo(name = "address")
    private String address;

    /**
     * 设备名称
     */
    @ColumnInfo(name = "device_name")
    private String deviceName;

    /**
     * 默认情况下，Room为实体中定义的每个字段创建一个列。如果实体有不想持久的字段，
     * 则可以使用@Ignore来注解它们
     */
    @Ignore
    private int status;

}
