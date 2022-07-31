package afkt_replace.component.base

import afkt_replace.core.app.AppContext
import afkt_replace.core.lib.base.core.AppChannel
import dev.kotlin.engine.log.log_dTag
import dev.utils.DevFinal

/**
 * detail: Base Application
 * @author Ttt
 */
class BaseApplication : AppContext() {

    // ==========
    // = 重写方法 =
    // ==========

    override fun onCreate() {
        super.onCreate()

        // 打印多渠道信息
        "apk_channel_flavors".log_dTag(
            message = "渠道：%s, 信息：%s",
            args = arrayOf(
                AppChannel.getChannel(),
                AppChannel.getChannelInfo(DevFinal.STR.TIME)
            )
        )
    }
}