package afkt_replace.module.person

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.ext.defaultMainContainerController
import afkt_replace.core.lib.base.controller.ui.ext.setExitBackIntercept
import afkt_replace.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import afkt_replace.core.lib.router.module.person.PersonRouter
import afkt_replace.module.person.databinding.PersonAppContainerBinding
import com.therouter.router.Route
import dev.mvvm.utils.toResString

@Route(path = PersonRouter.PATH_MAIN)
class MainContainerActivity : BaseAppActivity<PersonAppContainerBinding, PersonViewModel>(
    R.layout.person_app_container, BR.viewModel, simple_UITheme = {
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