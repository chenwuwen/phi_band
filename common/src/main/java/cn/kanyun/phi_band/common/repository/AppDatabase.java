package cn.kanyun.phi_band.common.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Room主要有以下三个部分组成：
 * 1.Database：
 * 标有 @Database注解的类需要具备以下特征：
 * 继承RoomDatabase的抽象类
 * 在注释中包括与数据库关联的实体列表（@Database(entities ={ })）
 * 包含一个无参的抽象方法并返回一个使用@Dao注解的类
 * 2.Entity：对应数据库中的表
 * 3.DAO：包含访问数据库的方法
 */
@Database(entities = {Device.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract DeviceDao deviceDao();

    /**
     * 在实例化AppDatabase对象的时候, 你应该使用单例模式, 因为每一个RoomDatabase实例都是非常耗时的, 而且你也应该很少访问多个实例
     *
     * @param context
     * @return
     */
    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "phi_band")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

}
