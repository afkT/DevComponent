package afkt_replace.core.base.app.extension.mvvm

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.base.app.base.ActivityVMType
import afkt_replace.core.base.app.base.inter.BindingActivityView
import afkt_replace.core.base.app.extension.theme.BaseUIThemeActivity
import androidx.databinding.ViewDataBinding

/**
 * detail: Base MVVM Activity
 * @author Ttt
 * 在 [BaseUIThemeActivity] 基础上进行关联赋值
 * 如果无特殊需求请使用 [BaseAppActivity]
 */
abstract class BaseMVVMActivity<VDB : ViewDataBinding, VM : BaseViewModel> : BaseUIThemeActivity<VDB, VM> {

    private var viewModelId: Int

    // ==========
    // = 构造函数 =
    // ==========

    constructor(
        bindLayoutId: Int,
        bindViewModelId: Int,
        vmType: ActivityVMType = ActivityVMType.ACTIVITY
    ) : super(bindLayoutId, null, vmType) {
        viewModelId = bindViewModelId
    }

    constructor(
        bindLayoutView: BindingActivityView?,
        bindViewModelId: Int,
        vmType: ActivityVMType = ActivityVMType.ACTIVITY
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