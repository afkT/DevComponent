package afkt_replace.core.lib.base.app.extension.loading

import afkt_replace.core.lib.base.app.BaseAppFragment
import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.FragmentVMType
import afkt_replace.core.lib.base.app.base.inter.BindingFragmentView
import afkt_replace.core.lib.base.app.base.simple.factory.SimpleFragmentIMPL
import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.controller.ui.theme.FragmentUITheme
import afkt_replace.core.lib.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonFactory
import androidx.databinding.ViewDataBinding

/**
 * detail: Base Loading Skeleton Fragment
 * @author Ttt
 * Loading UI 骨架 Fragment, 用于首次进入使用
 */
abstract class BaseLoadingSkeletonFragment<VDB : ViewDataBinding, VM : BaseViewModel> : BaseAppFragment<VDB, VM> {

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT,
        simple_Init: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)? = null
    ) : super(bindLayoutId, bindViewModelId, vmType) {
        simpleFactory = SimpleFragmentIMPL.of(
            simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
        )
    }

    constructor(
        bindLayoutView: BindingFragmentView,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT,
        simple_Init: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingSkeletonFragment<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)? = null
    ) : super(bindLayoutView, bindViewModelId, vmType) {
        simpleFactory = SimpleFragmentIMPL.of(
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

    private val simpleFactory: SimpleFragmentIMPL<BaseLoadingSkeletonFragment<VDB, VM>>

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

    override fun createFragmentUITheme(theme: FragmentUITheme): FragmentUITheme {
        return super.createFragmentUITheme(
            simpleFactory.simpleUITheme(theme)
        )
    }
}