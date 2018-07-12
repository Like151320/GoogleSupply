package like.googlesupply.transformations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import like.googlesupply.R

/**
 * 使用 Transformations 转换观察的 LiveData 的返回值
 * 原本观察 UserLiveData 会得到 UserBean 类型
 * 转换后 会得到 String 类型
 */
class TransformationsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transformations)

        //1、使用 Transformations.map 转换原有的 LiveData<UserBean> -> LiveData<String>
        val userLiveData: LiveData<String> = Transformations.map(UserLiveData()) { "${it.name}-${it.sex}" }
        //2、观察 LiveData<String>
        userLiveData.observe({ lifecycle }) {
            Log.i("Li_ke", "更新UI:$it")
        }

        // switchMap 与 map 类似，区别是需要返回一个 UserData 对象
//        val liveData2: LiveData<String> = Transformations.switchMap(UserLiveData()) { }
    }
}
