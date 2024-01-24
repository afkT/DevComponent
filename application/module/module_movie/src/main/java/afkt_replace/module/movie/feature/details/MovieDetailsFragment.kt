package afkt_replace.module.movie.feature.details

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.lib.router.module.movie.MovieRouter
import afkt_replace.module.movie.BR
import afkt_replace.module.movie.R
import afkt_replace.module.movie.databinding.MovieFragmentDetailsBinding
import com.therouter.router.Route

@Route(path = MovieRouter.PATH_MOVIE_DETAILS_FRAGMENT)
class MovieDetailsFragment : BaseLoadingSkeletonFragment<MovieFragmentDetailsBinding, MovieDetailsViewModel>(
    R.layout.movie_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 MovieDetailsFragment ViewModel 调用
        it.viewModel.initializeMovieDetailsFragment(it as MovieDetailsFragment)
    }
)