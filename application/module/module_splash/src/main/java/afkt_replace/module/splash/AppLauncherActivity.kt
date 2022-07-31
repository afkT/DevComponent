package afkt_replace.module.splash

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.splash.SplashNav
import afkt_replace.core.lib.router.module.splash.SplashRouter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal
import dev.utils.app.toast.ToastTintUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Route(path = SplashRouter.PATH_LAUNCHER, group = SplashRouter.GROUP)
class AppLauncherActivity : BaseActivityViewBinding<ViewBinding>() {

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun isAddTitleBar(): Boolean = false

    override fun isAddStatusBar(): Boolean = false

    override fun isStatusBarFrame(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val value = if (BuildConfig.isModular) "【模块化运行】" else "【整合运行】"

        ToastTintUtils.normal("延迟进入【首页容器页】")

        lifecycleScope.launch {
            delay(2000L)
            // 直接通过 postcard.navigation() 跳转会显示 AppTheme.Launcher style windowBackground
            val postcard = SplashNav.buildAppMain()
                .withObject(DevFinal.STR.STYLE, ThemeStyle(value))
            try {
                LogisticsCenter.completion(postcard)
                val intent = Intent(mActivity, postcard.destination)
                intent.putExtras(postcard.extras)
                startActivity(intent)
            } catch (e: Exception) {
            }
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