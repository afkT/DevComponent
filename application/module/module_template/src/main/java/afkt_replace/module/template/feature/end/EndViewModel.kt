package afkt_replace.module.template.feature.end

import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.split.data.IntentData
import afkt_replace.core.lib.config.ParamConst
import afkt_replace.module.template.TemplateRepository

class EndViewModel(
    private val repository: TemplateRepository = TemplateRepository()
) : BaseViewModel() {

    // 跳转传参
    private val intentData = IntentData.with()

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 EndFragment ViewModel 调用
     * @param fragment EndFragment
     */
    fun initializeEndFragment(fragment: EndFragment) {
        intentData.reader(fragment.arguments)
        // 初始化数据
        fragment.apply {
            // 初始化 CoreUiBaseHeaderBinding 通用 include layout
            uiController.initializeCoreUiBaseHeader(binding.vidHeader)
            // 设置标题
            uiController.appUI.title.set(intentData.get(ParamConst.TITLE))
            // 设置返回键
            binding.vidHeader.vidTitleBar.vidBackCl.setOnClickListener {
                fragment.finishActivity()
            }
        }
    }
}