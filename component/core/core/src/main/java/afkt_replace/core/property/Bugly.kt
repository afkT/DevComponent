package afkt_replace.core.property

import afkt_replace.core.app.AppContext
import afkt_replace.core.lib.base.core.AppChannel
import afkt_replace.core.lib.base.core.AppDebug
import android.os.Process
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import dev.utils.app.AppUtils
import dev.utils.app.ProcessUtils

/**
 * detail: Bugly 初始化
 * @author Ttt
 */
object Bugly {

    const val KEY_DEBUG = "xx"
    const val KEY_RELEASE = "xx"

    /**
     * 初始化 Bugly
     * @param appContext [AppContext]
     */
    fun initialize(appContext: AppContext) {
        appContext.getBuglyConfig()?.let { config ->
            // 用户配置策略
            val strategy = UserStrategy(appContext)
            // 获取当前包名
            val packageName = appContext.packageName
            // 获取当前进程名
            val processName = ProcessUtils.getProcessName(Process.myPid())
            // 设置上报进程控制
            strategy.isUploadProcess = processName == null || processName == packageName
            // 设置 App 版本
            strategy.appVersion = AppUtils.getAppVersionName()
            // 设置 App 包名
            strategy.appPackageName = packageName
            // 延迟初始化 10s
            strategy.appReportDelay = 10000L
            // 设置渠道信息
            strategy.appChannel = config.channel
            // 初始化
            CrashReport.initCrashReport(
                appContext, config.key, config.debug, strategy
            )
        }
    }
}

/**
 * detail: Bugly 配置信息
 * @author Ttt
 */
class BuglyConfig(
    val key: String,
    val debug: Boolean,
    val channel: String
)

/**
 * 返回默认 Bugly 配置
 */
fun defaultBuglyConfig(): BuglyConfig = BuglyConfig(
    key = (if (AppDebug.isOpenDebug()) Bugly.KEY_DEBUG else Bugly.KEY_RELEASE),
    debug = AppDebug.isOpenDebug(),
    channel = AppChannel.getChannel()
)