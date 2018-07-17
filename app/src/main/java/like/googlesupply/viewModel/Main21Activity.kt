package like.googlesupply.viewModel

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main21.*
import like.googlesupply.R

class Main21Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main21)

        ViewModelProviders.of(this).get("KEY",Main2ViewModel::class.java).getCurrentTime().observe({ lifecycle }) {
            tv.text = it
        }
    }
}
