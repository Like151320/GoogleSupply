package like.googlesupply.room

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import like.googlesupply.R
import like.googlesupply.R.id.all

class Main4Activity : AppCompatActivity() {
    //生成数据库实例
    val userDatabase by lazy { Room.databaseBuilder(applicationContext, UserDatabase::class.java, "userDatabase.db").build() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val subscribe = Observable.create<List<UserEntity>> {
            it.onNext(userDatabase.userDao().searchAllUsers())
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            Log.d("Li_ke", all.toString())
        }
    }
}

