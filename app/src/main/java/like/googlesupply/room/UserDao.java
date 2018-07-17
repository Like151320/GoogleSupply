package like.googlesupply.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * 作者: Li_ke
 * 日期: 2018/6/6 11:46
 * 作用: User 表操作接口
 */
@Dao
public interface UserDao {

    @Insert
    long[] insertUsers(UserEntity... users);

    @Query("select * from UserEntity")
    List<UserEntity> searchAllUsers();
}
