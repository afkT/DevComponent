package afkt_replace.core.base.app

import afkt_replace.core.lib.base.app.base.FragmentVMType
import afkt_replace.core.lib.base.app.base.inter.BindingFragmentView
import afkt_replace.core.lib.base.app.base.simple.factory.SimpleFragmentIMPL
import afkt_replace.core.lib.base.app.extension.mvvm.BaseMVVMFragment
import afkt_replace.core.lib.base.controller.ui.theme.FragmentUITheme
import androidx.databinding.ViewDataBinding

/**
 * detail: Base MVVM Fragment
 * @author Ttt
 * 如有额外参数等可统一在此基类增加, 避免污染底层基类过于复杂、混乱
 */
abstract class BaseAppFragment<VDB : ViewDataBinding, VM : afkt_replace.core.base.app.BaseViewModel> : BaseMVVMFragment<VDB, VM> {

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT
    ) : super(bindLayoutId, bindViewModelId, vmType)

    constructor(
        bindLayoutView: BindingFragmentView,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT
    ) : super(bindLayoutView, bindViewModelId, vmType)

    // ====================
    // = 敏捷简化开发扩展接口 =
    // ====================

    /**
     * 避免污染原始构造函数以及后续删减、扩展统一复制一份构造函数新增函数参数
     * 【后续基类不再复制该注释】如不考虑特殊化
     * 为了代码简洁可把下方构造方法覆盖上方代码
     */

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT,
        simple_Init: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
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
        simple_Init: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_Start: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_PreLoad: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_Agile: ((afkt_replace.core.base.app.BaseAppFragment<VDB, VM>) -> Unit)? = null,
        simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)? = null
    ) : super(bindLayoutView, bindViewModelId, vmType) {
        simpleFactory = SimpleFragmentIMPL.of(
            simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
        )
    }

    // ====================
    // = 敏捷简化开发扩展接口 =
    // ====================

    private var simpleFactory: SimpleFragmentIMPL<afkt_replace.core.base.app.BaseAppFragment<VDB, VM>>? = null

    override fun simpleInit() {
        simpleFactory?.simpleInit(this)
    }

    override fun simpleStart() {
        simpleFactory?.simpleStart(this)
    }

    override fun simpleAgile() {
        simpleFactory?.simpleAgile(this)
    }

    override fun simplePreLoad() {
        simpleFactory?.simplePreLoad(this)
    }

    // ===========================
    // = 敏捷简化开发扩展 - UITheme =
    // ===========================

    override fun createFragmentUITheme(theme: FragmentUITheme): FragmentUITheme {
        return super.createFragmentUITheme(
            simpleFactory?.simpleUITheme(theme) ?: theme
        )
    }
}