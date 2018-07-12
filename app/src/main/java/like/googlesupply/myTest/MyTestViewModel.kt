package like.googlesupply.myTest

import android.arch.lifecycle.ViewModel

/**
 * 作者: Li_ke
 * 日期: 2018/7/12 13:15
 * 作用:
 */
class MyTestViewModel : ViewModel() {
    val order = OrderLiveData()
    val nowTime = TimeLiveData()
}