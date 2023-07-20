package afkt_replace.core.base.app.base

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.simple.ISimpleAgile
import afkt_replace.core.lib.base.controller.BaseDataController
import afkt_replace.core.lib.base.controller.BaseTransitionController
import afkt_replace.core.lib.base.controller.BaseUIController
import afkt_replace.core.lib.base.controller.BaseVMController
import afkt_replace.core.lib.base.controller.inter.IController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import dev.DevUtils
import dev.base.expand.content.DevBaseContentMVVMFragment
import dev.utils.app.ActivityUtils
import dev.utils.common.ClassUtils

/**
 * detail: Base MVVM Fragment
 * @author Ttt
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : afkt_replace.core.base.app.BaseViewModel>(
    private val vmType: FragmentVMType = FragmentVMType.FRAGMENT
) : DevBaseContentMVVMFragment<VDB, VM>(),
    IController,
    ISimpleAgile {

    // Activity
    open val mActivity: FragmentActivity get() = requireActivity()

    // 基础 ViewModel 控制封装
    private val vmController: BaseVMController<VDB, VM> = BaseVMController(this)

    // 基础 UI 控制封装
    open val uiController: BaseUIController by lazy {
        BaseUIController(contentAssist, this, this)
    }

    // 基础 Data 控制封装
    open val dataController: BaseDataController by lazy {
        BaseDataController(arguments)
    }

    // 基础 Activity Transition Animation 跳转动画
    open val transitionController = BaseTransitionController(this)

    // =

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // 内部初始化前调用
        simpleInit()
        // 内部初始化
        innerInitialize()
        // 内部初始化后开始流程调用
        simpleStart()
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
        uiController.addSkeletonView(context)
        // 简化预加载
        simplePreLoad()
        // 预加载方法
        preLoad()
        // 初始化方法
        initOrder()
        // 敏捷开发简化调用
        simpleAgile()
    }

    // =====================
    // = IDevBaseViewModel =
    // =====================

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

        // =================
        // = initViewModel =
        // =================

        // 不放在 initViewModel() 中, 是防止重写 initViewModel() 忘记调用 super.initViewModel()

        try {
            val clazz = ClassUtils.getGenericSuperclass(this.javaClass, 1) as Class<VM>
            when (vmType) {
                FragmentVMType.FRAGMENT -> {
                    viewModelAssist.getFragmentViewModel(
                        this, clazz
                    )?.let {
                        innerViewModel(it)
                    }
                }

                FragmentVMType.ACTIVITY -> {
                    viewModelAssist.getActivityViewModel(
                        activity, clazz
                    )?.let {
                        innerViewModel(it)
                    }
                }

                FragmentVMType.APPLICATION -> {
                    viewModelAssist.getAppViewModel(
                        DevUtils.getApplication(), clazz
                    )?.let {
                        innerViewModel(it)
                    }
                }
            }
        } catch (e: Exception) {
            assist.printLog(e, "innerInitialize - initViewModel")
        }
    }

    /**
     * 内部 ViewModel 初始化
     * @param vmObject VM
     */
    private fun innerViewModel(vmObject: VM) {
        try {
            viewModel = vmObject
            lifecycle.addObserver(vmObject)
            // 初始化 ViewModel 属性值
            vmController.initialize(
                binding = binding,
                viewModel = vmObject,
                uiController = uiController,
                dataController = dataController,
                keyEventController = null
            )
        } catch (e: Exception) {
            assist.printLog(e, "innerViewModel")
        }
    }

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 关闭当前 Activity
     */
    fun finishActivity() {
        ActivityUtils.getManager().finishActivity(activity)
    }
}