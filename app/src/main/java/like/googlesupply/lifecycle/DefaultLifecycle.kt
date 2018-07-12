package like.googlesupply.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent

//2、生命周期观察者必须继承 LifecycleObserver 接口才能监听生命周期变化。
//3、LifecycleObserver 接口没有任何必须实现的方法，需要自己添加要监听哪些生命周期的变化。
interface DefaultLifecycle : LifecycleObserver {

    //4、添加生命周期变化监听的方式为：写一个方法，添加如下注解，注解的参数就是此方法监听的生命周期。
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(owner: LifecycleOwner){}

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner){}

    //5、有一定的灵活性：可以监听所有生命周期（ON_ANY）、可以将【被观察者】/【生命周期变化事件】作为参数传入方法中
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event){}
}
