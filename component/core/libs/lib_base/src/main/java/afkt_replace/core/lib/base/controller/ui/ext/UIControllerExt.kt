package afkt_replace.core.lib.base.controller.ui.ext

import afkt_replace.core.lib.base.controller.BaseUIController

// ===========================
// = BaseUIController 扩展函数 =
// ===========================

/**
 * 默认模块化首页入口 UI 控制样式
 */
fun BaseUIController.defaultMainContainerController(titleStr: String?): BaseUIController {
    appUI.apply {
        // 隐藏返回键
        backVisible.set(false)
        // 显示标题
        titleVisible.set(true)
        // 标题文案
        title.set(titleStr)
    }
    return this
}