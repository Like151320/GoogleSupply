package like.googlesupply.roomKotlin

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_room_kotlin.*
import like.googlesupply.R

/**
 * 总和使用 Demo
 * V：处理最简单的视图逻辑
 */
class RoomKotlinActivity : AppCompatActivity() {

    // V 中包含 VM
    private val viewModel by lazy {
        ViewModelProviders.of(this, RoomKotlinViewModel.Factory(application))
                .get(RoomKotlinViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_kotlin)

        // 业务事件转交 VM 处理
        btn.setOnClickListener { viewModel.addRandomBook() }
        subscribeUI(viewModel)
    }

    //监听 Data 变化
    private fun subscribeUI(viewModel: RoomKotlinViewModel) {

        viewModel.getBooks().observe({ lifecycle }) {
            tv.text = it!!.map { it.toString() }.toString()
        }

        viewModel.isLoading().observe({ lifecycle }) {
            progressBar.visibility = if (it!!) View.VISIBLE else View.INVISIBLE
        }
    }
}
