package like.googlesupply.allTest1

import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

/**
 * 作者: Li_ke
 * 日期: 2018/7/12 10:57
 * 作用:
 */
class LocationLiveData(context: Context) : LiveData<Location>() {
    private val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val listener: LocationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            //定位一旦改变就更新至 liveData
            value = p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    //出现观察者，开始监听值变化
    override fun onActive() {
        super.onActive()
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F, listener)
    }

    //观察者离开，停止监听值变化
    override fun onInactive() {
        super.onInactive()
        locationManager.removeUpdates(listener)
    }
}