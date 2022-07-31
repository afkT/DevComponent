package afkt_replace.module.template.feature

import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateFragmentBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal

@Route(path = TemplateRouter.PATH_TEMPLATE_FRAGMENT, group = TemplateRouter.GROUP)
class TemplateFragment : BaseFragmentViewBinding<TemplateFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.STYLE)
    var themeStyle: ThemeStyle? = null

    override fun baseLayoutId(): Int = R.layout.template_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.vidTitleTv.text = "$TAG \r\n ${themeStyle?.value}"

        themeStyle?.let { uiController.setAllBackground(it.color) }
    }
}