package afkt_replace.core.base.core

import afkt_replace.core.channel.AbstractChannelFlavors
import dev.base.DevVariable
import dev.utils.DevFinal

/**
 * detail: APP 渠道信息
 * @author Ttt
 */
object AppChannel : afkt_replace.core.channel.AbstractChannelFlavors {

    // 渠道信息实现
    private val IMPL: afkt_replace.core.channel.AbstractChannelFlavors by lazy {
        newChannelFlavorsIMPL()
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 是否未找到渠道实现
     * @return `true` yes, `false` no
     */
    fun isNotFoundChannel(): Boolean {
        return IMPL is NotFoundChannelFlavors
    }

    // ==========================
    // = AbstractChannelFlavors =
    // ==========================

    /**
     * 获取渠道名
     * @return 渠道名
     */
    override fun getChannel(): String {
        return IMPL.getChannel()
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
        return IMPL.getChannelInfo(key)
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
        return IMPL.getExtraInfo(key)
    }

    // ============
    // = 可读写数据 =
    // ============

    /**
     * 获取渠道变量操作基类
     * @return DevVariable<String, Any>
     */
    override fun getVariable(): DevVariable<String, Any> {
        return IMPL.getVariable()
    }

    /**
     * 操作渠道变量
     * @param operate 操作类型
     * @return `true` success, `false` fail
     */
    override fun opVariable(operate: String): Boolean {
        return IMPL.opVariable(operate)
    }

    // ==========
    // = 内部方法 =
    // ==========

    // 多渠道实现类名 ( 类名必须一致且包名位置相同 )
    private const val IMPL_CLASS_NAME = "afkt_replace.core.lib.channel.ChannelFlavorsIMPL"

    /**
     * 通过 class 创建多渠道实例
     * @return AbstractChannelFlavors IMPL
     */
    private fun newChannelFlavorsIMPL(): afkt_replace.core.channel.AbstractChannelFlavors {
        return try {
            val clazz = Class.forName(IMPL_CLASS_NAME)
            val channelIMPL = clazz.newInstance()
            channelIMPL as afkt_replace.core.channel.AbstractChannelFlavors
        } catch (e: Exception) {
            NotFoundChannelFlavors(e.toString())
        }
    }

    /**
     * detail: 未找到渠道实现
     * @author Ttt
     */
    private class NotFoundChannelFlavors(
        private val errorMessage: String
    ) : afkt_replace.core.channel.AbstractChannelFlavors {

        private val mVariable = DevVariable<String, Any>()

        override fun getChannel(): String {
            return DevFinal.STR.NOT_FOUND.uppercase()
        }

        override fun getChannelInfo(key: String): String {
            return errorMessage
        }

        override fun getExtraInfo(key: String): String {
            return errorMessage
        }

        override fun getVariable(): DevVariable<String, Any> {
            return mVariable
        }

        override fun opVariable(operate: String): Boolean {
            return false
        }
    }
}