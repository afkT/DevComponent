package afkt_replace.module.main

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import afkt_replace.core.lib.config.AppLibConfig
import afkt_replace.core.router.module.main.MainNav
import afkt_replace.core.router.module.main.MainRouter
import afkt_replace.module.main.databinding.MainAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

class AppLauncherActivity : BaseAppActivity<MainAppLauncherBinding, MainViewModel>(
    R.layout.main_app_launcher, BR.viewModel, simple_UITheme = {
        it.defaultAppLauncherUITheme()
    }
),
    CancelAdapt {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 非组件化编译运行直接关闭页面
        if (!BuildConfig.isModular) {
            finish()
            return
        }

        lifecycleScope.launch {
            delay(AppLibConfig.ROUTER_DELAY_MILLIS)
            MainNav.build(MainRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}