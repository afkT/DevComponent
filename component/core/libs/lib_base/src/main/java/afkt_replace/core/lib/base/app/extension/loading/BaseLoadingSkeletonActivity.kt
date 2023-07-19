package afkt_replace.core.lib.base.app.extension.loading

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.ActivityVMType
import afkt_replace.core.lib.base.app.base.inter.BindingActivityView
import afkt_replace.core.lib.base.app.base.simple.factory.SimpleActivityIMPL
import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.controller.ui.theme.ActivityUITheme
import afkt_replace.core.lib.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonFactory
import androidx.databinding.ViewDataBinding

/**
 * detail: Base Loading Skeleton Activity
 * @author Ttt
 * Loading UI 骨架 Activity, 用于首次进入使用
 */
abstract class BaseLoadingSkeletonActivity<VDB : ViewDataBinding, VM : BaseViewModel> : BaseAppActivity<VDB, VM> {

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: ActivityVMType = ActivityVMType.ACTIVITY,
        simple_Init: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((ActivityUITheme) -> ActivityUITheme)? = null
    ) : super(bindLayoutId, bindViewModelId, vmType) {
        simpleFactory = SimpleActivityIMPL.of(
            simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
        )
    }

    constructor(
        bindLayoutView: BindingActivityView?,
        bindViewModelId: Int,
        vmType: ActivityVMType = ActivityVMType.ACTIVITY,
        simple_Init: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingSkeletonActivity<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((ActivityUITheme) -> ActivityUITheme)? = null
    ) : super(bindLayoutView, bindViewModelId, vmType) {
        simpleFactory = SimpleActivityIMPL.of(
            simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
        )
    }

    // ===========
    // = Loading =
    // ===========

    // 基础 Loading Skeleton 控制封装
    open val loadingSkeletonController: BaseLoadingSkeletonController<VM> by lazy {
        BaseLoadingSkeletonController(contentAssist)
    }

    // Page Loading 骨架工厂类
    open val loadingSkeletonFactory = PageLoadingSkeletonFactory()

    // ============
    // = override =
    // ============

    override fun initViewModel() {
        super.initViewModel()

        // 初始化 ViewModel loadingSkeletonController
        loadingSkeletonController.initialize(viewModel)
    }

    // ====================
    // = 敏捷简化开发扩展接口 =
    // ====================

    private val simpleFactory: SimpleActivityIMPL<BaseLoadingSkeletonActivity<VDB, VM>>

    override fun simpleInit() {
        super.simpleInit()
        simpleFactory.simpleInit(this)
    }

    override fun simpleStart() {
        super.simpleStart()
        simpleFactory.simpleStart(this)
    }

    override fun simpleAgile() {
        super.simpleAgile()
        simpleFactory.simpleAgile(this)
    }

    override fun simplePreLoad() {
        super.simplePreLoad()
        simpleFactory.simplePreLoad(this)
    }

    // ===========================
    // = 敏捷简化开发扩展 - UITheme =
    // ===========================

    override fun createActivityUITheme(theme: ActivityUITheme): ActivityUITheme {
        return super.createActivityUITheme(
            simpleFactory.simpleUITheme(theme)
        )
    }
}