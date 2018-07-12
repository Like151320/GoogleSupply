package like.googlesupply.myTest

import android.arch.lifecycle.LiveData

/**
 * 作者: Li_ke
 * 日期: 2018/7/12 11:47
 * 作用:
 */
class OrderLiveData : LiveData<Order>() {
    fun changeStage(stage: Int) {
        value?.stage = stage
        postValue(value)
    }

    init {
        value = Order(110, 4)
    }
}