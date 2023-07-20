package afkt_replace.module.splash

import afkt_replace.core.project.router.module.splash.SplashNav
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.mvvm.utils.hi.hiif.hiIfNotNull

class SplashRepository {

    /**
     * 查询广告数据
     * @return LiveData<SplashAds>
     */
    fun queryAds(): LiveData<afkt_replace.core.project.bean.splash.SplashAds> {
        val liveData = MutableLiveData<afkt_replace.core.project.bean.splash.SplashAds>()
        // 获取广告数据
        SplashNav.splashProvider().hiIfNotNull({ provider ->
            provider.getAdsOb().let { ads ->
                ads.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(
                        sender: Observable?,
                        propertyId: Int
                    ) {
                        ads.get()?.let { data ->
                            // 移除监听回调防止多次触发
                            ads.removeOnPropertyChangedCallback(this)
                            // 查询成功进行通知
                            liveData.postValue(data)
                        }
                    }
                })
                // 查询广告数据
                provider.queryAds()
            }
        }, {
            liveData.postValue(afkt_replace.core.project.bean.splash.NONE_ADS)
        })
        return liveData
    }
}