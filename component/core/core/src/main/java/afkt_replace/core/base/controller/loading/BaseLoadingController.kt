package afkt_replace.core.base.controller.loading

import afkt_replace.core.base.app.BaseViewModel
import dev.base.utils.assist.DevBaseContentAssist
import dev.utils.DevFinal
import dev.widget.assist.ViewAssist

/**
 * detail: 基础 Loading 控制封装
 * @author Ttt
 * 区别于 Loading Skeleton 只用在首次进入管理控制
 * 该 Loading 是重复性使用在 Content Layout 上层显示
 */
class BaseLoadingController<VM : BaseViewModel>(
    val contentAssist: DevBaseContentAssist,
    val FORCED_SHOW: Boolean = true
) {
    // Loading 填充辅助类
    val viewAssist: ViewAssist

    init {
        // 如果 stateLinear 已经有其他用途则可以在 bodyFrame 后续添加一个新的布局
        viewAssist = ViewAssist.wrap(contentAssist.stateLinear)
    }

    /**
     * 初始化 ViewModel 属性值
     * @param viewModel VM
     */
    internal fun initialize(viewModel: VM) {
        viewModel.loadingController.value = this
        // 默认隐藏 Loading View
        viewAssist.goneWrapper()
    }

    // ==============
    // = 内部封装逻辑 =
    // ==============

    fun isVisibility(): Boolean {
        return viewAssist.isVisibleWrapper
    }

    fun isTypeNone(): Boolean {
        return viewAssist.isTypeView(DevFinal.DEFAULT.ERROR_INT)
    }

    fun isTypeIng(): Boolean {
        return viewAssist.isTypeIng
    }

    fun isTypeFailed(): Boolean {
        return viewAssist.isTypeFailed
    }

    fun isTypeSuccess(): Boolean {
        return viewAssist.isTypeSuccess
    }

    fun isTypeEmptyData(): Boolean {
        return viewAssist.isTypeEmptyData
    }

    // =

    fun showIng(
        notFoundOP: Boolean = true,
        forcedShow: Boolean = FORCED_SHOW
    ) {
        if (forcedShow) {
            viewAssist.showIng(notFoundOP)
        } else if (isVisibility()) {
            viewAssist.showIng(notFoundOP)
        }
    }

    fun showFailed(
        notFoundOP: Boolean = true,
        forcedShow: Boolean = FORCED_SHOW
    ) {
        if (forcedShow) {
            viewAssist.showFailed(notFoundOP)
        } else if (isVisibility()) {
            viewAssist.showFailed(notFoundOP)
        }
    }

    fun showSuccess(
        notFoundOP: Boolean = true,
        forcedShow: Boolean = FORCED_SHOW
    ) {
        if (forcedShow) {
            viewAssist.showSuccess(notFoundOP)
        } else if (isVisibility()) {
            viewAssist.showSuccess(notFoundOP)
        }
    }

    fun showEmptyData(
        notFoundOP: Boolean = true,
        forcedShow: Boolean = FORCED_SHOW
    ) {
        if (forcedShow) {
            viewAssist.showEmptyData(notFoundOP)
        } else if (isVisibility()) {
            viewAssist.showEmptyData(notFoundOP)
        }
    }
}