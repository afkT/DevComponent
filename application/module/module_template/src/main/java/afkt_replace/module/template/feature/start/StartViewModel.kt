package afkt_replace.module.template.feature.start

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.module.template.TemplateNavBuild
import afkt_replace.module.template.TemplateRepository
import android.view.View
import dev.mvvm.command.BindingConsumer

/**
 * detail: 模板 Module Start 演示 ViewModel
 * @author Ttt
 * 也可统一放到 TemplateViewModel, 拆分只是为了更加方便维护管理
 */
class StartViewModel(
    private val repository: TemplateRepository = TemplateRepository()
) : BaseViewModel() {

    val onClickEnd = object : BindingConsumer<View?> {
        override fun accept(value: View?) {
            TemplateNavBuild.routerEndPage()
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 StartFragment ViewModel 调用
     * @param fragment StartFragment
     */
    fun initializeStartFragment(fragment: StartFragment) {
        fragment.binding.apply {
        }
    }
}