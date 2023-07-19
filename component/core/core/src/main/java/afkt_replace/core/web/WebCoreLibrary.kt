package afkt_replace.core.web

import afkt_replace.core.web.assist.WebViewAssist
import android.webkit.WebSettings
import dev.expand.engine.log.log_d
import dev.utils.app.PathUtils

/**
 * detail: WebView Core Lib
 * @author Ttt
 */
object WebCoreLibrary {

    // ============
    // = 初始化方法 =
    // ============

    /**
     * 初始化 WebView 辅助类全局配置
     */
    fun initializeWebViewBuilder() {
        val builder: WebViewAssist.Builder = WebViewAssist.Builder()
            .setBuiltInZoomControls(false) // 显示内置缩放工具
            .setDisplayZoomControls(false) // 显示缩放工具
            .setAppCachePath( // Application Caches 地址
                PathUtils.getInternal().getAppCachePath("cache")
            )
            .setDatabasePath( // 数据库缓存路径
                PathUtils.getInternal().getAppCachePath("db")
            )
            .setRenderPriority(WebSettings.RenderPriority.HIGH) // 渲染优先级高
            .setOnApplyListener(object : WebViewAssist.Builder.OnApplyListener {
                override fun onApply(
                    webViewAssist: WebViewAssist?,
                    builder: WebViewAssist.Builder
                ) {
                    log_d(message = "WebViewAssist Builder onApply")
                }
            })
        // 基础布局算法
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING)
        } else {
            builder.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        }
        WebViewAssist.setGlobalBuilder(builder)
    }
}