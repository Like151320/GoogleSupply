package like.googlesupply.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

/**
ViewModel - 绑定 View 与 Data

 */
//4、实体类必须继承自 ViewModel。
class Main2ViewModel : ViewModel() {
    //5、数据类型必须被 LiveData 包裹一层，才能加入观察机制。
    private val timeLiveData: MutableLiveData<String> = MutableLiveData()

    fun getCurrentTime(): MutableLiveData<String> {
        Log.d("Li_ke", "getCurrentTime()")
        return timeLiveData
    }

    private fun test() {
        //3、改变数据
        timeLiveData.value = "str"//只能在主线程使用
        timeLiveData.postValue("str")//可在子线程使用
    }

    //没有任何绑定的View时触发
    override fun onCleared() {
        super.onCleared()
    }
}