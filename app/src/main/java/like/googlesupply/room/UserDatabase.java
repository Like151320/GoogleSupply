package like.googlesupply.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * 作者: Li_ke
 * 日期: 2018/6/6 11:47
 * 作用: 数据管理类
 */
@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static UserDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, UserDatabase.class, "userDatabase.db").build();
    }
}