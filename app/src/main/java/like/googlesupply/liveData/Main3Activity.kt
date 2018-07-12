package like.googlesupply.liveData

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main3.*
import like.googlesupply.R

/**
 * 当 LiveData 的 value 更新时，绑定的 View 随之更新，省去手动调用 ViewModule
 */
class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar?.title = "LiveData单例"

        Main3LiveData.observe({ lifecycle }) { time ->
            tv_liveData?.text = "$time"
        }
    }
}

/* 思考
Lifecycle 能做什么：

1、只需在 Activity/Fragment 中加一行代码，就能在任意类中监听它的生命周期，并且不会造成内存泄露。
        主要功能是低耦合的关联生命周期，一般有这种需求的类，比如 Presenter 会为了交互UI而维护一个 IView 引用，专门释放内存。
                只有那种 “需要知道页面的生命周期，但不操作页面”的类才需要这个功能。
2、维护 ViewModel 为 Activity/Fragment 设置数据页面绑定（在数据实体类改变时更新到页面上），并管理生命周期，不会浪费资源。
        单功能实用价值还行，实际使用时，数据必定是一个实体类，当一个值进行改变时，页面就会跟着变化，但因为其限制了“必须在主线程更新数据”导致在部分数据更新的时候需要手动切换线程。
                                    如果要再现有的MVP上整合它，那么得到的好处是：？
                                    比如一个定位Service，若没有订阅的页面，也就无需持续定位了。（但实际还得看业务）


 */