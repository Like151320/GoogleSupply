package like.googlesupply.roomKotlin

import android.app.Application
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.util.*
import kotlin.concurrent.thread

/**
 * 作者: Li_ke
 * 日期: 2018/7/13 14:09
 * 作用:
 */
class RoomKotlinViewModel(context: Context) : ViewModel() {
    private val database = BookDatabase.getInstance(context)

    //MediatorLiveData - 中介，可监听数据源
    private val isLoading: MediatorLiveData<Boolean> = MediatorLiveData()

    init {
        //仅当数据库 database.databaseReady(LiveData类型) 为true时,才判定加载完毕
        //默认正在加载中
        isLoading.postValue(true)
        //数据库初始化成功 -> 加载完毕
        isLoading.addSource(database.databaseReady) { isDatabaseReady ->
            //观察 source 事件
            if (isDatabaseReady == true)
                isLoading.postValue(false)
        }
    }

    //LiveData 是从 Database 得到的，但并不用切换线程
    fun getBooks() = database.bookDao().queryAllToLiveData()

    //是否在刷新中
    fun isLoading() = isLoading

    fun addRandomBook() {
        //需切换线程 —— 直接插入数据库 会导致因在 mainThread 操作 Database 而崩溃
        thread {
            database.bookDao().insertAll(listOf(BookEntity().also {
                it.userId = 1//必须有关联的外键
                it.name = "book:${Random().nextInt()}"
            }))
        }

    }

    //使用 Factory 定制 ViewModel 的创建参数 （此处使用到 application )
    class Factory(val application: Application) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RoomKotlinViewModel(application) as T
        }
    }
}