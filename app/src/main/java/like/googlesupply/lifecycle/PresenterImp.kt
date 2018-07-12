package like.googlesupply.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.util.Log

class PresenterImp : DefaultLifecycle {

    override fun onCreate() {
        log(null, Lifecycle.Event.ON_CREATE)
    }

    override fun onStart(owner: LifecycleOwner) {
        log(owner, Lifecycle.Event.ON_START)
    }

    override fun onResume(owner: LifecycleOwner) {
        log(owner, Lifecycle.Event.ON_RESUME)
    }

    override fun onPause(owner: LifecycleOwner) {
        log(owner, Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop(owner: LifecycleOwner) {
        log(owner, Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        log(owner, Lifecycle.Event.ON_DESTROY)

        //6、可以使用 owner.lifecycle 得到当前生命状态 与 移除观察者
        if (owner.lifecycle.currentState == Lifecycle.State.DESTROYED)
            owner.lifecycle.removeObserver(this)
    }

    // Any 是在其它指定声明周期的方法都调用后才调用的
    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
        log(owner, event)
    }

    private fun log(source: LifecycleOwner?, event: Lifecycle.Event? = null) {
        Log.d("Li_ke", "${source?.lifecycle?.currentState}: $event")
    }
}