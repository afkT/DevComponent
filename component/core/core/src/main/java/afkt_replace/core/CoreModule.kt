package afkt_replace.core

import afkt_replace.core.network.HttpCoreLibrary
import afkt_replace.core.web.WebCoreLibrary
import android.content.Context

/**
 * detail: Core Module ( ContentProvider Initializer )
 * @author Ttt
 */
class CoreModule private constructor() : BaseModule(CoreModule::class.java.simpleName) {

    companion object {
        val instance: CoreModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CoreModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
        // 初始化 OkHttp 管理库 ( Retrofit 多 BaseUrl 管理、Progress 监听 )
        HttpCoreLibrary.initialize(context)
        // 初始化 WebView 辅助类全局配置
        WebCoreLibrary.initializeWebViewBuilder()
    }
}