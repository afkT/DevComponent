package afkt_replace.core.base.app.extension.loading

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.FragmentVMType
import afkt_replace.core.lib.base.app.base.inter.BindingFragmentView
import afkt_replace.core.lib.base.app.base.simple.factory.SimpleFragmentIMPL
import afkt_replace.core.lib.base.controller.loading.BaseLoadingController
import afkt_replace.core.lib.base.controller.ui.theme.FragmentUITheme
import androidx.databinding.ViewDataBinding

/**
 * detail: Base Loading Fragment
 * @author Ttt
 * 区别于 Loading Skeleton 只用在首次进入管理控制
 * 该 Loading 是重复性使用在 Content Layout 上层显示
 */
abstract class BaseLoadingFragment<VDB : ViewDataBinding, VM : afkt_replace.core.base.app.BaseViewModel> : BaseLoadingSkeletonFragment<VDB, VM> {

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT,
        simple_Init: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
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
        simple_Init: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((BaseLoadingFragment<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)? = null
    ) : super(bindLayoutView, bindViewModelId, vmType) {
        simpleFactory = SimpleFragmentIMPL.of(
            simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
        )
    }

    // ===========
    // = Loading =
    // ===========

    // 基础 Loading 控制封装
    open val loadingController: BaseLoadingController<VM> by lazy {
        BaseLoadingController(contentAssist)
    }

    // ============
    // = override =
    // ============

    override fun initViewModel() {
        super.initViewModel()

        // 初始化 ViewModel loadingController
        loadingController.initialize(viewModel)
    }

    // ====================
    // = 敏捷简化开发扩展接口 =
    // ====================

    private val simpleFactory: SimpleFragmentIMPL<BaseLoadingFragment<VDB, VM>>

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