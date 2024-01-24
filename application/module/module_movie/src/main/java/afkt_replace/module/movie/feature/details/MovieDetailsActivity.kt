package afkt_replace.module.movie.feature.details

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.defaultActivityBlankUITheme
import afkt_replace.core.lib.router.module.movie.MovieRouter
import afkt_replace.module.movie.BR
import afkt_replace.module.movie.MovieViewModel
import afkt_replace.module.movie.R
import afkt_replace.module.movie.databinding.MovieActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.therouter.router.Route

@Route(path = MovieRouter.PATH_MOVIE_DETAILS_ACTIVITY)
class MovieDetailsActivity : BaseAppActivity<MovieActivityDetailsBinding, MovieViewModel>(
    R.layout.movie_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.MovieDetailsFragment, it.intent.extras)
    }
)