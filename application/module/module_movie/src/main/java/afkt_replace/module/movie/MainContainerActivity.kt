package afkt_replace.module.movie

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.ext.defaultMainContainerController
import afkt_replace.core.lib.base.controller.ui.ext.setExitBackIntercept
import afkt_replace.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import afkt_replace.core.router.module.movie.MovieRouter
import afkt_replace.module.movie.databinding.MovieAppContainerBinding
import com.alibaba.android.arouter.facade.annotation.Route
import dev.mvvm.utils.toResString

@Route(path = MovieRouter.PATH_MAIN, group = MovieRouter.GROUP)
class MainContainerActivity : afkt_replace.core.base.app.BaseAppActivity<MovieAppContainerBinding, MovieViewModel>(
    R.layout.movie_app_container, BR.viewModel, simple_UITheme = {
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