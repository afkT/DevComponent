package afkt_replace.core.lib.property

import android.annotation.SuppressLint
import android.app.Application
import com.github.moduth.blockcanary.BlockCanary
import com.github.moduth.blockcanary.BlockCanaryContext
import dev.utils.LogPrintUtils
import dev.utils.app.AppUtils
import dev.utils.app.NetWorkUtils

/**
 * detail: BlockCanary 初始化
 * @author Ttt
 */
object BlockCanaryKT {

    fun initialize(application: Application) {
        // 在主进程初始化调用哈
        BlockCanary.install(application, AppBlockCanaryContext()).start()
    }
}

class AppBlockCanaryContext : BlockCanaryContext() {

    private val TAG: String = BlockCanaryKT::class.java.simpleName

    override fun provideQualifier(): String {
        var qualifier = ""
        try {
            qualifier += "core_${AppUtils.getAppVersionCode()}_${AppUtils.getAppVersionName()}"
        } catch (e: Exception) {
            LogPrintUtils.eTag(TAG, "provideQualifier", e)
        }
        return qualifier
    }

    @SuppressLint("MissingPermission")
    override fun provideNetworkType(): String {
        var type = "unknown"
        try {
            type = NetWorkUtils.getNetworkType().name
        } catch (e: Exception) {
            LogPrintUtils.eTag(TAG, "provideNetworkType", e)
        }
        return type
    }
}