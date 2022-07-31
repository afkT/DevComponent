package afkt_replace.core.lib.base.app

import afkt_replace.core.lib.base.controller.BaseController
import afkt_replace.core.lib.base.controller.BaseUIController
import afkt_replace.core.lib.ui.widget.BaseTitleBar
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import dev.base.expand.content.DevBaseContentMVVMFragment
import dev.utils.app.ScreenUtils
import dev.utils.common.ClassUtils

/**
 * detail: Base MVVM Fragment
 * @author Ttt
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : DevBaseContentMVVMFragment<VDB, VM>(),
    BaseController {

    // 基础 UI 控制封装
    protected val uiController: BaseUIController by lazy { BaseUIController(contentAssist) }

    // =

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 是否安全处理 addView
        contentAssist.setSafe(isContentAssistSafe())
        super.onCreateView(inflater, container, savedInstanceState)
        // 内部初始化
        innerInitialize()
        // 预加载方法
        preLoad()
        return mContentView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化 ViewModel
        initViewModel()
        // 添加基础骨架 View
        addBaseView()
        // 初始化方法
        initOrder()
    }

    // ==========
    // = 接口方法 =
    // ==========

    override fun baseLayoutView(): View? = null

    override fun isAddTitleBar(): Boolean = false

    override fun isAddStatusBar(): Boolean = false

    override fun isStatusBarFrame(): Boolean = false

    // ==========
    // = 内部操作 =
    // ==========

    // StatusBar View
    lateinit var statusBar: View

    // TitleBar View
    lateinit var titleBar: BaseTitleBar

    /**
     * 添加基础骨架 View
     */
    private fun addBaseView() {
        context?.let {
            if (isAddTitleBar()) {
                titleBar = BaseTitleBar(it)
                contentAssist.addTitleView(titleBar)
            }
            if (isAddStatusBar()) {
                statusBar = View(it)
                statusBar.setBackgroundColor(Color.WHITE)
                val statusBarHeight: Int = ScreenUtils.getStatusBarHeight()
                statusBar.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight
                )
                contentAssist.addStatusBarView(statusBar)
            }
        }
    }

    // =====================
    // = IDevBaseViewModel =
    // =====================

    override fun initViewModel() {
        try {
            val clazz = ClassUtils.getGenericSuperclass(this.javaClass, 1) as Class<VM>
            viewModelAssist.getActivityViewModel(
                activity, clazz
            )?.let {
                viewModel = it
            }
        } catch (e: Exception) {
            assist.printLog(e, "initViewModel")
        }
    }

    // =============
    // = 内部初始化 =
    // =============

    private fun innerInitialize() {
        try {
            ARouter.getInstance().inject(this)
        } catch (e: Exception) {
            assist.printLog(e, "ARouter inject")
        }
    }
}