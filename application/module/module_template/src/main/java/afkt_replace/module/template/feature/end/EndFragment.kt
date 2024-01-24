package afkt_replace.module.template.feature.end

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.module.template.BR
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateFragmentEndBinding
import com.therouter.router.Route

@Route(path = TemplateRouter.PATH_END_FRAGMENT)
class EndFragment : BaseLoadingSkeletonFragment<TemplateFragmentEndBinding, EndViewModel>(
    R.layout.template_fragment_end, BR.viewModel, simple_Agile = {
        // 初始化 EndFragment ViewModel 调用
        it.viewModel.initializeEndFragment(it as EndFragment)
    }
)