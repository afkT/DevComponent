package afkt_replace.module.movie.feature.popular

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.router.module.movie.MovieRouter
import afkt_replace.module.movie.BR
import afkt_replace.module.movie.R
import afkt_replace.module.movie.databinding.MovieFragmentPopularBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = MovieRouter.PATH_POPULAR_FRAGMENT, group = MovieRouter.GROUP)
class PopularMovieFragment : BaseLoadingSkeletonFragment<MovieFragmentPopularBinding, PopularMovieViewModel>(
    R.layout.movie_fragment_popular, BR.viewModel, simple_Agile = {
        // 初始化 PopularMovieFragment ViewModel 调用
        it.viewModel.initializePopularMovieFragment(it as PopularMovieFragment)
    }
)