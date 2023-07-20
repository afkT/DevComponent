package afkt_replace.module.splash

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.config.KeyConst
import afkt_replace.core.project.bean.splash.SplashAds
import afkt_replace.core.project.router.module.splash.SplashNav
import afkt_replace.core.router.AppRouter
import android.app.Activity
import androidx.lifecycle.LiveData
import dev.expand.engine.json.toJson
import dev.mvvm.utils.toResString
import dev.utils.app.toast.ToastTintUtils

class SplashViewModel(
    private val repository: SplashRepository = SplashRepository()
) : BaseViewModel() {

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 查询广告数据
     * @return LiveData<SplashAds>
     */
    fun queryAds(): LiveData<SplashAds> {
        return repository.queryAds()
    }

    /**
     * 路由跳转 App 首页入口
     * @param ads 广告数据
     * 进入首页后打开
     */
    fun routerAppMain(
        activity: Activity,
        ads: SplashAds? = null
    ) {
        if (BuildConfig.isModular) {
            ToastTintUtils.success(R.string.str_modular_app.toResString())
        } else {
            // 直接通过 postcard.navigation() 跳转会显示 AppTheme.Launcher style windowBackground
            val postcard = SplashNav.buildAppMain()
            ads?.let {
                if (it.previewTime > 0L) {
                    postcard.withString(KeyConst.ADS, it.toJson())
                }
            }
            // 获取跳转 Intent
            AppRouter.routerIntent(activity, postcard)?.let { intent ->
                activity.startActivity(intent)
            }
            activity.finish()
        }
    }
}