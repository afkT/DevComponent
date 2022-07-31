package afkt_replace.module.user

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.user.UserNav
import afkt_replace.core.lib.router.module.user.UserRouter
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils

@Route(path = UserRouter.PATH_MAIN, group = UserRouter.GROUP)
class MainContainerActivity : BaseActivityViewBinding<ViewBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.STYLE)
    var themeStyle: ThemeStyle? = null

    override fun baseLayoutId(): Int = 0

    override fun isViewBinding(): Boolean = false

    override fun isContentAssistSafe(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("${BuildConfig.MODULE_NAME} 首页容器页")
            .setTitleColor(Color.WHITE)
            .goneBackView()

        themeStyle?.let { uiController.setAllBackground(it.color) }

        (UserNav.build(UserRouter.PATH_USER_FRAGMENT)
            .with(intent.extras).navigation() as? Fragment)?.let { fragment ->
            supportFragmentManager.beginTransaction().apply {
                add(contentAssist.contentLinear?.id ?: View.NO_ID, fragment)
                commit()
            }
        }
    }

    override fun onBackPressed() {
        if (!ClickUtils.isFastDoubleClick(DevFinal.STR.TAG, 1500L)) {
            ToastTintUtils.info("再按一次，退出应用")
            return
        }
        super.onBackPressed()
    }
}