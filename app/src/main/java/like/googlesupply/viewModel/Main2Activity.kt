package like.googlesupply.viewModel

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import like.googlesupply.R
import like.googlesupply.liveData.Main3Activity
import java.text.SimpleDateFormat
import java.util.*

// 使用 ViewModel 管理数据，实现 Data 与 View生命该周期 的关联 （当生命周期为stop时，不去显示更新数据）
/**
ViewModule - 视图模型
作用：
1、配合 lifecycle，仅当页面在前台时刷新数据
2、使用观察者模式 绑定 View ViewModule ，由 viewModule 主导改变显示的值
3、将 Date 与 逻辑 从 View 中脱离，直到关联的 View 销毁时才消失，即 旋转等导致重建时，数据仍然保留

使用场景：
当 Data 改变时，使用 ViewModule 更新 View

 */
class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.title = "Data 与 View 生命周期管理框架"

        //1、注册 Fragment/Activity，使用反射获取 ViewModel 实例
        val viewModel = ViewModelProviders.of(this).get("KEY",Main2ViewModel::class.java)

        //2、添加监听：当 ViewModel 的某个值改变时进行显示
        viewModel.getCurrentTime().observe({ lifecycle }) { time ->
            tv_time.text = time
        }

        //3、在需要的时候改变这个值，就会触发监听。getCurrentTime() 只会被调用一次 必须在主线程进行值改变
        btn_updateTime.setOnClickListener {
            viewModel.getCurrentTime().value =
                    SimpleDateFormat("HH:mm:ss", Locale.CHINA).format(Date())
        }

        btn_jumpNextDataActivity.setOnClickListener { startActivity(Intent(this, Main3Activity::class.java)) }
        btn_jumpModelActivity.setOnClickListener { startActivity(Intent(this, Main21Activity::class.java)) }
    }
}

/*
意图将 LiveData 从Activity2 传给 Activity21，
但是 ViewModel 可以以Activity为主，跨Fragment使用，却不能夸Activity使用。
LiveData 可以设为单例，但是单例会占用整个生命周期的内存。
若使用单例+手动实现API进行内存优化，那么就要面对把数据保存在哪里的问题。

如果是在多个Activity间共享数据的LiveData，需设为单例，或创建统一的LiveDataManager来获取其引用。
而内存的管理应当手动实现，在有Activity时开始，在所有Activity退出后清空。清空内存意味着单例只占用一个对象的内存，即可以忽略了。


 */