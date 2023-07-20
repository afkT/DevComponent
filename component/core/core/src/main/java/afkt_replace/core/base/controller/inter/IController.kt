package afkt_replace.core.base.controller.inter

import afkt_replace.core.base.controller.transition.ITransitionController
import afkt_replace.core.base.controller.ui.IUIController

/**
 * detail: Base 汇总控制器接口
 * @author Ttt
 */
interface IController : IUIController,
    ITransitionController {

    // =======================
    // = ControllerViewModel =
    // =======================

    /**
     * 是否初始化 ControllerViewModel 相关参数
     * @return `true` yes, `false` no
     */
    fun isControllerViewModelInit(): Boolean = true
}