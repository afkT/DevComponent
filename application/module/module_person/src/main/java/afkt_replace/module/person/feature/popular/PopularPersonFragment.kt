package afkt_replace.module.person.feature.popular

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.router.module.person.PersonRouter
import afkt_replace.module.person.BR
import afkt_replace.module.person.R
import afkt_replace.module.person.databinding.PersonFragmentPopularBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = PersonRouter.PATH_POPULAR_FRAGMENT, group = PersonRouter.GROUP)
class PopularPersonFragment : BaseLoadingSkeletonFragment<PersonFragmentPopularBinding, PopularPersonViewModel>(
    R.layout.person_fragment_popular, BR.viewModel, simple_Agile = {
        // 初始化 PopularPersonFragment ViewModel 调用
        it.viewModel.initializePopularPersonFragment(it as PopularPersonFragment)
    }
)