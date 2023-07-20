package afkt_replace.module.tv.feature.details

import afkt_replace.core.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.project.router.module.tv.TvRouter
import afkt_replace.module.tv.BR
import afkt_replace.module.tv.R
import afkt_replace.module.tv.databinding.TvFragmentDetailsBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TvRouter.PATH_TV_DETAILS_FRAGMENT, group = TvRouter.GROUP)
class TvDetailsFragment : BaseLoadingSkeletonFragment<TvFragmentDetailsBinding, TvDetailsViewModel>(
    R.layout.tv_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 TvDetailsFragment ViewModel 调用
        it.viewModel.initializeTvDetailsFragment(it as TvDetailsFragment)
    }
)