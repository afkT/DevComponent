package afkt_replace.core.lib.property

import android.content.Context
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

    val KEY = "bugly_key"

    /**
     * 初始化 Bugly
     * @param context [Context]
     * @param config [BuglyConfig]
     */
    fun initialize(
        context: Context,
        config: BuglyConfig
    ) {
        // 用户配置策略
        val strategy = UserStrategy(context)
        // 获取当前包名
        val packageName = context.packageName
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
            context, config.key, config.debug, strategy
        )
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