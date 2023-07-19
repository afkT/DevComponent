package afkt_replace.core.lib.base.app.extension.mvvm

import afkt_replace.core.lib.base.app.BaseAppFragment
import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.app.base.FragmentVMType
import afkt_replace.core.lib.base.app.base.inter.BindingFragmentView
import afkt_replace.core.lib.base.app.extension.theme.BaseUIThemeFragment
import androidx.databinding.ViewDataBinding

/**
 * detail: Base MVVM Fragment
 * @author Ttt
 * 在 [BaseUIThemeFragment] 基础上进行关联赋值
 * 如果无特殊需求请使用 [BaseAppFragment]
 */
abstract class BaseMVVMFragment<VDB : ViewDataBinding, VM : BaseViewModel> : BaseUIThemeFragment<VDB, VM> {

    private var viewModelId: Int

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT
    ) : super(bindLayoutId, null, vmType) {
        viewModelId = bindViewModelId
    }

    constructor(
        bindLayoutView: BindingFragmentView,
        bindViewModelId: Int,
        vmType: FragmentVMType = FragmentVMType.FRAGMENT
    ) : super(0, bindLayoutView, vmType) {
        viewModelId = bindViewModelId
    }

    // =====================
    // = IDevBaseViewModel =
    // =====================

    override fun initViewModel() {
        super.initViewModel()
        try {
            // 关联 ViewModel 对象值
            binding.setVariable(viewModelId, viewModel)
        } catch (_: Exception) {
        }
    }
}