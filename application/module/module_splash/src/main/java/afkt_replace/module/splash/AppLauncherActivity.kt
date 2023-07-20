package afkt_replace.module.splash

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.router.module.splash.SplashRouter
import afkt_replace.lib.splash.ads.SplashAdsEvent
import afkt_replace.module.splash.databinding.SplashActivityBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.jeremyliao.liveeventbus.LiveEventBus
import dev.expand.engine.json.toJson
import dev.utils.app.ActivityUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

@Route(path = SplashRouter.PATH_LAUNCHER, group = SplashRouter.GROUP)
class AppLauncherActivity : afkt_replace.core.base.app.BaseAppActivity<SplashActivityBinding, SplashViewModel>(
    R.layout.splash_activity, BR.viewModel, simple_UITheme = {
        it.defaultAppLauncherUITheme()
    }
),
    CancelAdapt {

    // ============
    // = 初始化操作 =
    // ============

    override fun onBackPressed() {
        ActivityUtils.getManager().finishAllActivity()
        finish()
    }

    override fun initListener() {
        super.initListener()

        LiveEventBus.get(SplashAdsEvent::class.java)
            .observe(this) {
                viewModel.routerAppMain(mActivity, it.ads)
            }

        // 获取广告数据
        viewModel.queryAds().observe(this) { ads ->
            if (ads.previewTime <= 0) {
                viewModel.routerAppMain(mActivity, ads)
            } else {
                lifecycleScope.launch(Dispatchers.Main) {
                    val args = IntentData.with().setAds(ads.toJson())
                    binding.navContainer.findNavController().navigate(
                        R.id.SplashAdsFragment, args.insert()
                    )
                }
            }
        }
    }
}