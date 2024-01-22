package afkt_replace.module.template

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.base.controller.ui.ext.defaultMainContainerController
import afkt_replace.core.base.controller.ui.ext.setExitBackIntercept
import afkt_replace.core.base.controller.ui.theme.defaultMainContainerUITheme
import afkt_replace.core.project.router.module.template.TemplateRouter
import afkt_replace.module.template.databinding.TemplateAppContainerBinding
import com.therouter.router.Route
import dev.mvvm.utils.toResString

@Route(path = TemplateRouter.PATH_MAIN)
class MainContainerActivity : BaseAppActivity<TemplateAppContainerBinding, TemplateViewModel>(
    R.layout.template_app_container, BR.viewModel, simple_UITheme = {
        it.defaultMainContainerUITheme()
    }, simple_PreLoad = {
        it.apply {
            uiController.defaultMainContainerController(
                R.string.app_name.toResString()
            )
            // 设置返回键退出 App 拦截监听
            keyEventController.setExitBackIntercept(BuildConfig.MODULE_NAME)
        }
    }
)