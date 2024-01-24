package afkt_replace.module.tv.feature.details

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import afkt_replace.core.lib.router.module.tv.TvRouter
import afkt_replace.module.tv.BR
import afkt_replace.module.tv.R
import afkt_replace.module.tv.TvViewModel
import afkt_replace.module.tv.databinding.TvActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.therouter.router.Route

@Route(path = TvRouter.PATH_TV_DETAILS_ACTIVITY)
class TvDetailsActivity : BaseAppActivity<TvActivityDetailsBinding, TvViewModel>(
    R.layout.tv_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.TvDetailsFragment, it.intent.extras)
    }
)