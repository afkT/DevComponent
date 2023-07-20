package afkt_replace.core.base.controller.ui.ext

import afkt_replace.core.R
import afkt_replace.core.base.controller.BaseKeyEventController
import dev.mvvm.utils.toResString
import dev.utils.app.ClickUtils
import dev.utils.app.toast.ToastTintUtils
import dev.utils.common.able.Interceptable

// =================================
// = BaseKeyEventController 扩展函数 =
// =================================

/**
 * 设置返回键退出 App 拦截监听
 * @param tag 双击校验 Key
 * @param intervalTime 双击时间间隔
 * @return [Interceptable.Intercept]
 */
fun BaseKeyEventController.setExitBackIntercept(
    tag: String,
    intervalTime: Long = afkt_replace.core.config.AppLibConfig.BACK_EXIT_INTERVAL_TIME
): BaseKeyEventController {
    exitBackIntercept = createExitBackIntercept(tag, intervalTime)
    return this
}

/**
 * 创建返回键退出 App 拦截监听
 * @param tag 双击校验 Key
 * @param intervalTime 双击时间间隔
 * @return [Interceptable.Intercept]
 */
fun createExitBackIntercept(
    tag: String,
    intervalTime: Long = afkt_replace.core.config.AppLibConfig.BACK_EXIT_INTERVAL_TIME
): Interceptable.Intercept<Boolean> {
    return object : Interceptable.Intercept<Boolean> {
        override fun intercept(): Boolean {
            if (!ClickUtils.isFastDoubleClick(tag, intervalTime)) {
                ToastTintUtils.info(R.string.str_tip_exit_app.toResString())
                return true
            }
            return false
        }
    }
}