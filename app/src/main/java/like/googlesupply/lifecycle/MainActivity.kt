package like.googlesupply.lifecycle

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import like.googlesupply.R
import like.googlesupply.room.Main4Activity
import like.googlesupply.viewModel.Main2Activity

/**
Lifecycle - 生命周期

作用是：
不用重写onCreate()等回调的情况下，观察 Activity/Fragment 的生命周期

使用场景：
1、若一个模块 与 具体页面 的关联只有生命周期，则可以使用此框架解耦。
2、若不想很麻烦的在 具体页面 的各个声明周期回调中调用 一个模块 对应的生命周期方法，则可以使用此框架简化

导入：
repositories: google()//添加 google() 依赖库
//    lifecycle = "1.1.1" (LiveData也在此包)
implementation "android.arch.lifecycle:runtime:$lifecycle"
implementation "android.arch.lifecycle:extensions:$lifecycle"
annotationProcessor "android.arch.lifecycle:compiler:$lifecycle"
//    room = "1.1.0"
implementation "android.arch.persistence.room:runtime:$room"
annotationProcessor "android.arch.persistence.room:compiler:$room"
 */
class MainActivity : AppCompatActivity() {

    private val mPresenter: DefaultLifecycle = PresenterImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "观察生命周期"

        //1、添加一个生命周期观察者
        lifecycle.addObserver(mPresenter)

        tv_jumpNew.setOnClickListener { startActivity(Intent(this@MainActivity, Main2Activity::class.java)) }

        btn_jumpNewRoom.setOnClickListener { startActivity(Intent(this@MainActivity, Main4Activity::class.java)) }
    }
}
