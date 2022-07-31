package afkt_replace.module.wan_android

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.wan_android.WanAndroidNav
import afkt_replace.core.lib.router.module.wan_android.WanAndroidRouter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import dev.utils.DevFinal
import dev.utils.app.toast.ToastTintUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppLauncherActivity : BaseActivityViewBinding<ViewBinding>() {

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun isAddTitleBar(): Boolean = false

    override fun isAddStatusBar(): Boolean = false

    override fun isStatusBarFrame(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 非组件化编译运行直接关闭页面
        if (!BuildConfig.isModular) {
            finish()
            return
        }

        ToastTintUtils.normal("延迟进入【首页容器页】")

        lifecycleScope.launch {
            delay(2000L)
            WanAndroidNav.build(WanAndroidRouter.PATH_MAIN)
                .withObject(DevFinal.STR.STYLE, ThemeStyle("【模块化运行】"))
                .navigation()
            finish()
        }
    }

    override fun baseLayoutView(): View {
        return ImageView(this).apply {
            setBackgroundResource(R.drawable.launcher_gradient_bg)
            scaleType = ImageView.ScaleType.FIT_XY
        }
    }
}