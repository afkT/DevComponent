package afkt_replace.core.lib.base.controller

import dev.base.utils.assist.DevBaseContentAssist

/**
 * detail: 基类控制器接口
 * @author Ttt
 */
interface BaseController {

    /**
     * 是否添加 TitleBar
     */
    fun isAddTitleBar(): Boolean = true

    /**
     * 是否添加 StatusBar
     * @return `true` yes, `false` no
     */
    fun isAddStatusBar(): Boolean = true

    /**
     * StatusBar 是否占位显示 ( StatusBar 底部透明 )
     * @return `true` yes, `false` no
     */
    fun isStatusBarFrame(): Boolean = true

    /**
     * [DevBaseContentAssist] 是否安全处理
     * @return `true` yes, `false` no
     */
    fun isContentAssistSafe(): Boolean = false
}