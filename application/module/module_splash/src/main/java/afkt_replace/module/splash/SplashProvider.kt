package afkt_replace.module.splash

import afkt_replace.core.project.bean.splash.SplashAds
import afkt_replace.core.project.router.module.splash.ISplashProvider
import afkt_replace.core.router.BaseProviderExt
import afkt_replace.lib.splash.ads.SplashAdsUtils
import androidx.databinding.ObservableField
import com.therouter.inject.ServiceProvider

//@Route(path = SplashRouter.PATH_SPLASH_PROVIDER)
class SplashProvider : ISplashProvider,
    BaseProviderExt(SplashProvider::class.java.simpleName) {

    init {
        SplashAdsUtils.instance.init()
    }

    // ===================
    // = ISplashProvider =
    // ===================

    override fun getAdsOb(): ObservableField<SplashAds> {
        return SplashAdsUtils.instance.getAdsOb()
    }

    override fun queryAds() {
        SplashAdsUtils.instance.queryAds()
    }

    override fun insertAds(list: List<SplashAds>): Boolean {
        return SplashAdsUtils.instance.insertAds(list)
    }
}

@ServiceProvider(returnType = ISplashProvider::class)
fun theRouterServiceProvider(): ISplashProvider = SplashProvider()