package like.googlesupply.roomKotlin

import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import like.googlesupply.room.UserDao
import like.googlesupply.room.UserEntity
import kotlin.concurrent.thread

/**
 * 数据库
 *
 * 若生成架构文件,还需：
//指定room.schemaLocation生成的文件路径
javaCompileOptions {
annotationProcessorOptions {
arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
}
}
 */

//抽象的
//entities 执行的是 Entity 类，不是 Dao 类 (因写错折腾了2小时)
@Database(entities = [BookEntity::class, UserEntity::class],//数据库中的实体
        version = 1,//版本
        exportSchema = false)//是否生成架构文件,用于json格式查看数据库内容 (配置、数据)
abstract class BookDatabase : RoomDatabase() {

    //LiveData 并不只是用于数据绑定，由于它生命周期自管理和观察者的特性，可用于事件监听 —— 数据库准备完毕监听
    val databaseReady = MutableLiveData<Boolean>()

    //抽象Dao方法
    abstract fun bookDao(): BookDao

    //由于两个表关联,必须在同一个数据库中存储（数据库的规定）
    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "bookDatabase.db"
        private var instance: BookDatabase? = null//应当是单例

        fun getInstance(context: Context): BookDatabase {
            instance = instance ?: synchronized(BookDatabase::class.java) {
                val database = Room.databaseBuilder(context, BookDatabase::class.java, DATABASE_NAME).addCallback(object : Callback() {
                    /**第一次创建表时回调，通常在这里初始化表内容*/
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        thread {

                            BookDatabase.instance!!.userDao().insertUsers(UserEntity().also { it.id = 1 })
                            val list = (0..10).map { i ->
                                BookEntity().also {
                                    it.name = "book:$i"
                                    it.userId = 1
                                }
                            }
                            BookDatabase.instance!!.bookDao().insertAll(list)

                            //初始化 OK
                            BookDatabase.instance!!.databaseReady.postValue(true)
                        }
                    }
                }).build()
                //数据库已存在
                if (context.getDatabasePath(DATABASE_NAME).exists())
                    database.databaseReady.postValue(true)

                return@synchronized database
            }


            return instance!!
        }
    }
}
