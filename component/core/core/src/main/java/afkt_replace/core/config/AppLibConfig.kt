package afkt_replace.core.config

import dev.utils.app.ClickUtils

/**
 * detail: App Lib 配置信息
 * @author Ttt
 */
object AppLibConfig {

    // ============
    // = AppDebug =
    // ============

    /**
     * 是否 Release 版本标记 ( 用于标记 APK 是否 Release 发布版本 )
     */
    internal fun isRelease(): Boolean = BuildConfig.isRelease

    /**
     * 是否标记 Debug 模式 ( 与 APK build 模式无关, 用于控制第三方库 debug 标记 )
     */
    internal fun isOpenDebug(): Boolean = BuildConfig.openDebug

    /**
     * 是否开启日志
     */
    internal fun isOpenLog(): Boolean = BuildConfig.openLog

    /**
     * 是否开启优化检测、调试工具 ( 控制 UETool、LeakCanary、BlockCanary 等开关 )
     */
    internal fun isDebugTools(): Boolean = BuildConfig.showDebugTools

    // =============
    // = core/libs =
    // =============

    // ============
    // = lib_base =
    // ============

    // 是否安全处理 release 设置为 true
    val UI_CONTENT_ASSIST_SAFE: Boolean = isRelease()

    // ============
    // = lib_mvvm =
    // ============

    // 是否打印日志 ( 用于控制 MVVM 模块 )
    val MVVM_PRINT_LOG = isOpenLog()

    // 双击间隔时间
    const val MVVM_INTERVAL_TIME = ClickUtils.INTERVAL_TIME

    // ==========
    // = 其他配置 =
    // ==========

    // 双击返回键退出 APP 间隔时间
    const val BACK_EXIT_INTERVAL_TIME = 1500L

    // 启动延迟时间
    const val START_DELAY_MILLIS = 1500L

    // 默认延迟时间
    const val DEFAULT_DELAY_MILLIS = 2000L

    // 路由跳转延迟时间
    const val ROUTER_DELAY_MILLIS = 2000L

    // ==============
    // = 动画时间相关 =
    // ==============

    // 通用动画持续时间
    const val ANIM_DURATION_MILLIS = 500L

    // ViewAssist 过度动画持续时间
    const val ANIM_VIEW_ASSIST_DURATION_MILLIS = 500L

    // View 中心圆圈辐射动画持续时间
    const val ANIM_VIEW_CIRCULAR_DURATION_MILLIS = 800L
}