package afkt_replace.module.template.feature.start

import afkt_replace.core.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.project.router.module.template.TemplateRouter
import afkt_replace.module.template.BR
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateFragmentStartBinding
import com.therouter.router.Route

@Route(path = TemplateRouter.PATH_START_FRAGMENT)
class StartFragment : BaseLoadingSkeletonFragment<TemplateFragmentStartBinding, StartViewModel>(
    R.layout.template_fragment_start, BR.viewModel, simple_Agile = {
        // 初始化 StartFragment ViewModel 调用
        it.viewModel.initializeStartFragment(it as StartFragment)
    }
)