package afkt_replace.core.lib.debug

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.base.controller.ui.theme.defaultMainContainerUITheme
import afkt_replace.core.lib.debug.databinding.CoreDebugAssistMainActivityBinding
import android.graphics.drawable.ColorDrawable
import android.view.View
import dev.mvvm.base.attribute.Margins
import dev.mvvm.command.BindingConsumer
import dev.mvvm.utils.size.AppSize
import dev.mvvm.utils.toResString
import dev.utils.app.ResourceUtils

/**
 * detail: Debug 入口容器 Activity
 * @author Ttt
 */
class DebugMainContainerActivity : BaseAppActivity<CoreDebugAssistMainActivityBinding, DebugAssistViewModel>(
    R.layout.core_debug_assist_main_activity, BR.viewModel, simple_UITheme = {
        it.defaultMainContainerUITheme()
    }
) {

    override fun isSyncSkin(): Boolean {
        return false // 不进行同步换肤
    }

    override fun preLoad() {
        super.preLoad()

        uiController.also {
            // APP UI 通用控制
            it.appUI.apply {
                // 设置标题
                title.set(R.string.str_project_debug_accessibility.toResString())
            }
            // APP 主题样式资源类
            it.appThemeRes.apply {
                // 设置状态栏颜色
                statusBarBackground.value = ColorDrawable(
                    ResourceUtils.getColor(
                        dev.capture.compiler.R.color.dev_http_capture_include_tab_bg_color
                    )
                )
                // 设置标题栏高度
                titleBarHeight.value = AppSize.dp2px(56.0F)
                // 设置标题栏颜色
                titleBarBackground.value = ColorDrawable(
                    ResourceUtils.getColor(
                        dev.capture.compiler.R.color.dev_http_capture_include_tab_bg_color
                    )
                )
                // 设置返回键样式
                titleBackIcon.value = ResourceUtils.getDrawable(
                    dev.capture.compiler.R.drawable.dev_http_capture_back
                )
                // 设置返回键边距
                titleBackMargin.value = Margins()
            }
            // APP 通用事件
            it.appListener.apply {
                onClickBack = object : BindingConsumer<View> {
                    override fun accept(value: View) {
                        finish()
                    }
                }
            }
        }
    }
}