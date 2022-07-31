package afkt_replace.module.main

import afkt_replace.core.lib.base.app.BaseActivityViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.main.MainRouter
import afkt_replace.module.main.databinding.MainActivityBinding
import afkt_replace.module.main.feature.adapter.MainAdapter
import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils

@Route(path = MainRouter.PATH_MAIN, group = MainRouter.GROUP)
class MainContainerActivity : BaseActivityViewBinding<MainActivityBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.STYLE)
    var themeStyle: ThemeStyle? = null

    override fun baseLayoutId(): Int = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        titleBar.setTitle("${BuildConfig.MODULE_NAME} 首页容器页")
            .setTitleColor(Color.WHITE)
            .goneBackView()

        themeStyle?.let { uiController.setAllBackground(it.color) }

        if (!BuildConfig.isModular) {
            binding.vidVp.adapter = MainAdapter(themeStyle, this)
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