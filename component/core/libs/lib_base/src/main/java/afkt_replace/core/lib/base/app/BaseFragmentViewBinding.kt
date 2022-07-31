package afkt_replace.core.lib.base.app

import afkt_replace.core.lib.base.controller.BaseController
import afkt_replace.core.lib.base.controller.BaseUIController
import afkt_replace.core.lib.ui.widget.BaseTitleBar
import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import dev.base.able.IDevBaseViewModel
import dev.base.expand.content.DevBaseContentViewBindingFragment
import dev.base.utils.assist.DevBaseViewModelAssist
import dev.utils.app.ScreenUtils

/**
 * detail: Base ViewBinding Fragment
 * @author Ttt
 */
abstract class BaseFragmentViewBinding<VB : ViewBinding> : DevBaseContentViewBindingFragment<VB>(),
    BaseController,
    IDevBaseViewModel<ViewModel> {

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

    @JvmField // DevBase ViewModel 辅助类
    protected var viewModelAssist = DevBaseViewModelAssist()

    override fun initViewModel() {
    }

    // ============
    // = 内部初始化 =
    // ============

    private fun innerInitialize() {
        try {
            ARouter.getInstance().inject(this)
        } catch (e: Exception) {
            assist.printLog(e, "ARouter inject")
        }
    }

    // =====================
    // = Activity Provider =
    // =====================

    override fun <T : ViewModel> getActivityViewModel(modelClass: Class<T>): T? {
        return viewModelAssist.getActivityViewModelCache(activity, modelClass)
    }

    // =====================
    // = Fragment Provider =
    // =====================

    override fun <T : ViewModel> getFragmentViewModel(modelClass: Class<T>): T? {
        return null
    }

    override fun <T : ViewModel> getFragmentViewModel(
        fragment: Fragment?,
        modelClass: Class<T>
    ): T? {
        return viewModelAssist.getFragmentViewModel(fragment, modelClass)
    }

    // ========================
    // = Application Provider =
    // ========================

    override fun <T : ViewModel> getAppViewModel(
        application: Application?,
        modelClass: Class<T>
    ): T? {
        return viewModelAssist.getAppViewModel(application, modelClass)
    }

    override fun getAppViewModelProvider(application: Application?): ViewModelProvider? {
        return viewModelAssist.getAppViewModelProvider(application)
    }

    override fun getAppFactory(application: Application?): ViewModelProvider.Factory? {
        return viewModelAssist.getAppFactory(application)
    }
}