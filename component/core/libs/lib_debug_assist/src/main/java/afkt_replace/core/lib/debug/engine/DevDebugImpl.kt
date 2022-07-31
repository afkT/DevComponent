package afkt_replace.core.lib.debug.engine

import afkt_replace.core.lib.debug.floating.FloatingDebug
import afkt_replace.core.lib.engine.debug.IDebugEngine
import androidx.fragment.app.FragmentActivity
import okhttp3.OkHttpClient

/**
 * detail: Debug 编译辅助开发 Engine 实现
 * @author Ttt
 */
internal class DevDebugImpl : IDebugEngine {

    // ================
    // = IDebugEngine =
    // ================

    /**
     * 设置 Debug 功能开关
     * @param display 是否显示 Debug 功能
     */
    override fun setDebugFunction(display: Boolean) {
        FloatingDebug.instance.setDebugFunction(display)
    }

    /**
     * 连接 ( 显示 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    override fun attachDebug(activity: FragmentActivity?) {
        FloatingDebug.instance.attachDebug(activity)
    }

    /**
     * 分离 ( 隐藏 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    override fun detachDebug(activity: FragmentActivity?) {
        FloatingDebug.instance.detachDebug(activity)
    }

    /**
     * 添加抓包拦截器
     * @param builder    OkHttpClient Builder
     * @param moduleName 模块名 ( 要求唯一性 )
     */
    override fun addInterceptor(
        builder: OkHttpClient.Builder?,
        moduleName: String?
    ) {
        FloatingDebug.instance.addInterceptor(
            builder, moduleName
        )
    }
}