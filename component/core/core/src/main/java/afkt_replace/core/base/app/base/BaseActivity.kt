package afkt_replace.core.base.app.base

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.simple.ISimpleAgile
import afkt_replace.core.lib.base.controller.*
import afkt_replace.core.lib.base.controller.inter.IController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import dev.DevUtils
import dev.base.expand.content.DevBaseContentMVVMActivity
import dev.utils.common.ClassUtils

/**
 * detail: Base MVVM Activity
 * @author Ttt
 */
abstract class BaseActivity<VDB : ViewDataBinding, VM : afkt_replace.core.base.app.BaseViewModel>(
    private val vmType: afkt_replace.core.base.app.base.ActivityVMType = afkt_replace.core.base.app.base.ActivityVMType.ACTIVITY
) : DevBaseContentMVVMActivity<VDB, VM>(),
    IController,
    ISimpleAgile {

    // Activity
    open val mActivity: AppCompatActivity get() = this

    // 基础 ViewModel 控制封装
    private val vmController: BaseVMController<VDB, VM> = BaseVMController(this)

    // 基础 UI 控制封装
    open val uiController: BaseUIController by lazy {
        BaseUIController(contentAssist, this, this)
    }

    // 基础 Data 控制封装
    open val dataController: BaseDataController by lazy {
        BaseDataController(intent.extras)
    }

    // 基础 KeyEvent 物理按键 控制封装
    open val keyEventController = BaseKeyEventController()

    // 基础 Activity Transition Animation 跳转动画
    open val transitionController = BaseTransitionController(this)

    // =

    override fun onCreate(savedInstanceState: Bundle?) {
        // 跳转动画设置
        transitionController.createBeforeOnTransformation(this)
        // 初始化 UI 样式
        uiController.initialize(this)
        super.onCreate(savedInstanceState)
        // 内部初始化前调用
        simpleInit()
        // 内部初始化
        innerInitialize()
        // 内部初始化后开始流程调用
        simpleStart()
        // 初始化 ViewModel
        initViewModel()
        // 添加基础骨架 View
        uiController.addSkeletonView(this)
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
                afkt_replace.core.base.app.base.ActivityVMType.ACTIVITY -> {
                    viewModelAssist.getActivityViewModel(
                        this, clazz
                    )?.let {
                        innerViewModel(it)
                    }
                }

                afkt_replace.core.base.app.base.ActivityVMType.APPLICATION -> {
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
                keyEventController = keyEventController
            )
        } catch (e: Exception) {
            assist.printLog(e, "innerViewModel")
        }
    }

    // =====================
    // = override activity =
    // =====================

    override fun onBackPressed() {
        // 返回键拦截监听
        if (keyEventController.backIntercept?.intercept() == true) {
            return
        }
        // 返回键退出 App 拦截监听
        if (keyEventController.exitBackIntercept?.intercept() == true) {
            return
        }
        super.onBackPressed()
    }
}