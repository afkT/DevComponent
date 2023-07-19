package afkt_replace.module.template.feature.start

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.module.template.BR
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateFragmentStartBinding
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = TemplateRouter.PATH_START_FRAGMENT, group = TemplateRouter.GROUP)
class StartFragment : BaseLoadingSkeletonFragment<TemplateFragmentStartBinding, StartViewModel>(
    R.layout.template_fragment_start, BR.viewModel, simple_Agile = {
        // 初始化 StartFragment ViewModel 调用
        it.viewModel.initializeStartFragment(it as StartFragment)
    }
)