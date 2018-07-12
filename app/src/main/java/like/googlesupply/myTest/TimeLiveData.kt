package like.googlesupply.myTest

import android.arch.lifecycle.LiveData
import java.util.*

/**
 * 作者: Li_ke
 * 日期: 2018/7/12 11:47
 * 作用:
 */
class TimeLiveData : LiveData<Long>() {

    private val timer: Timer = Timer()

    init {
    }

    override fun onActive() {
        super.onActive()
        timer.schedule(object : TimerTask() {
            override fun run() {
                postValue(System.currentTimeMillis())
            }
        }, 0, 1000)
    }

    override fun onInactive() {
        super.onInactive()
        timer.cancel()
        timer.purge()
    }
}