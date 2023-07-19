package afkt_replace.core.lib.base.app.extension.theme

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.ActivityVMType
import afkt_replace.core.lib.base.app.base.BaseActivity
import afkt_replace.core.lib.base.app.base.inter.BindingActivityView
import afkt_replace.core.lib.base.controller.ui.theme.ActivityUITheme
import android.view.View
import androidx.databinding.ViewDataBinding

/**
 * detail: Base Theme Activity
 * @author Ttt
 * 在 [BaseActivity] 基础上封装 UITheme 样式 Intent 传参控制处理
 * 如果无特殊需求请使用 [BaseAppActivity]
 */
abstract class BaseUIThemeActivity<VDB : ViewDataBinding, VM : BaseViewModel> constructor(
    private val bindLayoutId: Int = 0,
    private val bindLayoutView: BindingActivityView? = null,
    vmType: ActivityVMType = ActivityVMType.ACTIVITY
) : BaseActivity<VDB, VM>(vmType) {

    // ==================
    // = IDevBaseLayout =
    // ==================

    /**
     * 获取 Layout Id ( 用于 contentLinear addView )
     * @return Layout Id
     */
    override fun baseLayoutId(): Int {
        return bindLayoutId
    }

    /**
     * 获取 Layout View ( 用于 contentLinear addView )
     * @return Layout View
     */
    override fun baseLayoutView(): View? {
        return bindLayoutView?.bind(this, layoutInflater)
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    private val activityUITheme: ActivityUITheme by lazy {
        createActivityUITheme(ActivityUITheme.with(intent))
    }

    /**
     * 初始化创建 ActivityUITheme
     * @return [ActivityUITheme]
     */
    open fun createActivityUITheme(theme: ActivityUITheme): ActivityUITheme {
        return theme
    }

    // =================
    // = IUIController =
    // =================

    override fun isContentAssistSafe(): Boolean {
        return activityUITheme.isContentAssistSafe()
    }

    // ===========
    // = Base UI =
    // ===========

    override fun isStatusBarFrame(): Boolean {
        return activityUITheme.isStatusBarFrame()
    }

    override fun isLightMode(): Boolean {
        return activityUITheme.isLightMode()
    }

    override fun isAddStatusBar(): Boolean {
        return activityUITheme.isAddStatusBar()
    }

    override fun isAddTitleBar(): Boolean {
        return activityUITheme.isAddTitleBar()
    }

    // ===============
    // = Window Flag =
    // ===============

    override fun isFlagFullScreen(): Boolean {
        return activityUITheme.isFlagFullScreen()
    }

    override fun isFlagKeepScreen(): Boolean {
        return activityUITheme.isFlagKeepScreen()
    }

    override fun isFlagSecure(): Boolean {
        return activityUITheme.isFlagSecure()
    }

    // ==================
    // = Window Feature =
    // ==================

    override fun isFeatureNoTitle(): Boolean {
        return activityUITheme.isFeatureNoTitle()
    }
}