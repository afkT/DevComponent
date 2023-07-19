package afkt_replace.module.template

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultAppLauncherUITheme
import afkt_replace.core.lib.config.AppLibConfig
import afkt_replace.core.lib.router.module.template.TemplateNav
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.module.template.databinding.TemplateAppLauncherBinding
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

class AppLauncherActivity : BaseAppActivity<TemplateAppLauncherBinding, TemplateViewModel>(
    R.layout.template_app_launcher, BR.viewModel, simple_UITheme = {
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
            TemplateNav.build(TemplateRouter.PATH_MAIN).navigation()
            finish()
        }
    }
}