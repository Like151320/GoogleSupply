package like.googlesupply.allTest1

import android.arch.lifecycle.LiveData
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import like.googlesupply.R

/**
 * 自动实现效果：
 * 1、页面销毁时，自动移除，不会内存残留。
 * 2、当页面在后台时，不会更新UI导致异常。
 * 3、只需要一个LocationLiveData就可以通知多个页面。
 */
class AllTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_test)

        val locationLiveData: LiveData<Location> = LocationLiveData(this)
        locationLiveData.observe({ lifecycle }) {
            Log.i("Li_ke", "更新UI:$it")
        }
    }
}
