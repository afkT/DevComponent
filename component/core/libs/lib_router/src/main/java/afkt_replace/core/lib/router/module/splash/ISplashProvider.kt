package afkt_replace.core.lib.router.module.splash

import afkt_replace.core.lib.bean.splash.SplashAds
import afkt_replace.core.lib.router.BaseProvider
import androidx.databinding.ObservableField

/**
 * detail: Splash 各个组件通讯接口
 * @author Ttt
 */
interface ISplashProvider : BaseProvider {

    /**
     * 获取广告数据监听对象
     * @return ObservableField<SplashAds>
     */
    fun getAdsOb(): ObservableField<SplashAds>

    /**
     * 查询广告数据
     */
    fun queryAds()

    /**
     * 插入广告数据
     * @param list List<SplashAds>
     * @return Boolean
     */
    fun insertAds(list: List<SplashAds>): Boolean
}