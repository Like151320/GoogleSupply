package like.googlesupply.liveData

import android.arch.lifecycle.LiveData
import android.os.Handler
import android.os.Looper
import java.util.*

/**
LiveData - 存活数据

有观察者时，生命唤醒
无观察者时，生命DIE

使用 LiveData 为数据添加观察方式
自定义 LiveData 为单例
 */
//继承LiveData 实现自定义单个数据
object Main3LiveData : LiveData<Int>() {

    private val mainHandler = Handler(Looper.getMainLooper())
    private var timer: Timer? = null

    init {
        //初始值
        value = value ?: 1
    }

    override fun onActive() {
        super.onActive()
        startTimer()
    }

    //只要有激活的监听者，该方法就不会被调用 == 当没有激活的监听者，该方法就会被调用。
    override fun onInactive() {
        super.onInactive()
        stopTimer()
    }

    //开始计时自加
    private fun startTimer() {
        if (timer != null) return
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                mainHandler.post {
                    value = (System.currentTimeMillis() / 1000).toInt()
                }
            }
        }, 0, 1000)
    }

    //停止计时自加
    private fun stopTimer() {
        timer?.purge()
        timer?.cancel()
        timer = null
    }
}