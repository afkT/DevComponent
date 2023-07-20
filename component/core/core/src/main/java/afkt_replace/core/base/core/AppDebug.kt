package afkt_replace.core.base.core

import afkt_replace.core.lib.base.BuildConfig

/**
 * detail: Base App 编译信息
 * @author Ttt
 */
object AppDebug {

    /**
     * 是否 Release 版本标记 ( 用于标记 APK 是否 Release 发布版本 )
     */
    fun isRelease(): Boolean = BuildConfig.isRelease

    /**
     * 是否标记 Debug 模式 ( 与 APK build 模式无关, 用于控制第三方库 debug 标记 )
     */
    fun isOpenDebug(): Boolean = BuildConfig.openDebug

    /**
     * 是否开启日志
     */
    fun isOpenLog(): Boolean = BuildConfig.openLog

    /**
     * 是否开启优化检测、调试工具 ( 控制 UETool、LeakCanary、BlockCanary 等开关 )
     */
    fun isDebugTools(): Boolean = BuildConfig.showDebugTools
}