package afkt_replace.core.lib.channel

import afkt_replace.component.BuildConfig
import dev.base.DevVariable
import dev.utils.DevFinal
import java.util.*

/**
 * detail: 默认 APP ( 官方 ) 渠道实现
 * @author Ttt
 */
class ChannelFlavorsIMPL : AbstractChannelFlavors {

    private val mVariable = DevVariable<String, Any>()

    // ==========================
    // = AbstractChannelFlavors =
    // ==========================

    /**
     * 获取渠道名
     * @return 渠道名
     */
    override fun getChannel(): String {
        return BuildConfig.CHANNEL_ID
    }

    // ==========
    // = 渠道信息 =
    // ==========

    /**
     * 获取指定 Key 渠道信息 ( 只读 )
     * @param key 指定 Key
     * @return 指定渠道信息
     */
    override fun getChannelInfo(key: String): String? {
        return MAP_CHANNEL_INFO[key]
    }

    // ==============
    // = 额外携带信息 =
    // ==============

    /**
     * 获取指定 Key 渠道额外携带信息 ( 只读 )
     * @param key 指定 Key
     * @return 指定渠道额外携带信息
     */
    override fun getExtraInfo(key: String): String? {
        return MAP_EXTRA_INFO[key]
    }

    // ============
    // = 可读写数据 =
    // ============

    /**
     * 获取渠道变量操作基类
     * @return DevVariable<String, Any>
     */
    override fun getVariable(): DevVariable<String, Any> {
        return mVariable
    }

    /**
     * 操作渠道变量
     * @param operate 操作类型
     * @return `true` success, `false` fail
     */
    override fun opVariable(operate: String): Boolean {
        return true
    }

    // ==============
    // = 不可修改 Map =
    // ==============

    // 渠道信息 ( 只读 )
    private val MAP_CHANNEL_INFO: Map<String, String>

    // 渠道额外携带信息 ( 只读 )
    private val MAP_EXTRA_INFO: Map<String, String>

    init {
        val channelInfoMap = mutableMapOf<String, String>().apply {
            put(DevFinal.STR.TIME, "${BuildConfig.CHANNEL_ID}-${BuildConfig.BUILD_DATE}")
        }
        MAP_CHANNEL_INFO = Collections.unmodifiableMap(channelInfoMap)

        val extraInfoMap = mutableMapOf<String, String>().apply {
            put(DevFinal.STR.INFO, "${BuildConfig.BUILD_TYPE}-${BuildConfig.VERSION_NAME}")
        }
        MAP_EXTRA_INFO = Collections.unmodifiableMap(extraInfoMap)
    }
}