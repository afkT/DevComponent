package afkt_replace.module.person

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import afkt_replace.core.config.AppLibConfig
import afkt_replace.core.router.module.person.PersonNav
import afkt_replace.core.router.module.person.PersonRouter
import afkt_replace.module.person.databinding.PersonAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

class AppLauncherActivity : afkt_replace.core.base.app.BaseAppActivity<PersonAppLauncherBinding, PersonViewModel>(
    R.layout.person_app_launcher, BR.viewModel, simple_UITheme = {
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
            PersonNav.build(PersonRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}