package like.googlesupply.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * 作者: Li_ke
 * 日期: 2018/6/6 11:46
 * 作用: Dao 类接口
 */
@Dao
public interface UserDao {

    @Insert
    public long[] insertUsers(UserEntity... users);

    @Insert
    public void insertUserList(List<UserEntity> users);

    @Update
    public void updateUsers(UserEntity... users);

    @Delete
    public void deleteUsers(UserEntity... users);

    @Query("select * from UserEntity")
    public List<UserEntity> searchAllUsers();

    @Query("select * from UserEntity where :age > 18")
    public List<UserEntity> searchUsersByAge(int age);

//    @Query("select firstName,age from UserEntity")
//    public List<UserEntity> searchAllSimpleUsers();

    @Query("select * from UserEntity where firstName like :name limit 1")
    public UserEntity searchUserByName(String name);
}
