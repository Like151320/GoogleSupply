package like.googlesupply.myTest

import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_my_test.*
import like.googlesupply.R
import java.text.SimpleDateFormat
import java.util.*

class MyTestActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MyTestViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_test)

        viewModel.order.observe({ lifecycle }) {
            tv_orderDetails.text = "${it?.id}:${it?.stage}"
        }

        button.setOnClickListener {
            //在页面中直接访问数据，如果是通过 ViewModel 访问数据反而更麻烦，因为若有多个ViewModel，则每个ViewModel都要提供接口
            viewModel.order.changeStage(viewModel.order.value!!.stage + 1)
//            viewModel.order.value?.run {
//                stage++
//            }
        }

        Transformations.map(viewModel.nowTime) {
            SimpleDateFormat("hh:mm:ss", Locale.CHINA).format(Date(it))
        }.observe({ lifecycle }) {
            tv_time.text = it
        }


    }
}
