package like.googlesupply.roomKotlin

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Dao:操作表
 */
@Dao
interface BookDao {//接口类型

    @Insert
    fun insertAll(bookEntity: List<BookEntity>)

    @Query(value = "select * from BookEntity where userId = :id")//查询语句, :id = 传入方法的参数
    fun queryToLiveData(id: Int): LiveData<List<BookEntity>>//直接将查询结果封装为 LiveData 来观察数据库数据

    @Query(value = "select * from BookEntity where userId = :id")
    fun query(id: Int): List<BookEntity>

    @Query(value = "select * from BookEntity")
    fun queryAllToLiveData(): LiveData<List<BookEntity>>
}
