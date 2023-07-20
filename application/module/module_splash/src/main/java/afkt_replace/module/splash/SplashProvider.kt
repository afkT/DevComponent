package afkt_replace.module.splash

import afkt_replace.core.router.BaseProviderExt
import afkt_replace.core.project.router.module.splash.ISplashProvider
import afkt_replace.core.project.router.module.splash.SplashRouter
import afkt_replace.lib.splash.ads.SplashAdsUtils
import android.content.Context
import androidx.databinding.ObservableField
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = SplashRouter.PATH_SPLASH_PROVIDER, group = SplashRouter.GROUP)
class SplashProvider : ISplashProvider,
    BaseProviderExt(SplashProvider::class.java.simpleName) {

    override fun init(context: Context?) {
        SplashAdsUtils.instance.init(context)
    }

    // ===================
    // = ISplashProvider =
    // ===================

    override fun getAdsOb(): ObservableField<afkt_replace.core.project.bean.splash.SplashAds> {
        return SplashAdsUtils.instance.getAdsOb()
    }

    override fun queryAds() {
        SplashAdsUtils.instance.queryAds()
    }

    override fun insertAds(list: List<afkt_replace.core.project.bean.splash.SplashAds>): Boolean {
        return SplashAdsUtils.instance.insertAds(list)
    }
}