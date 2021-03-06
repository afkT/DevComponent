package dev.core.app

import android.webkit.WebSettings
import com.alibaba.android.arouter.launcher.ARouter
import dev.core.assist.WebViewAssist
import dev.core.lib.base.BaseAppContext
import dev.core.property.BlockCanaryKT
import dev.core.property.Bugly
import dev.core.property.BuglyConfig
import dev.core.property.defaultBuglyConfig
import dev.engine.log.DevLogEngine
import dev.utils.app.PathUtils

/**
 * detail: App Base Application
 * @author Ttt
 */
open class AppContext : BaseAppContext() {

    override fun onCreate() {
        super.onCreate()

        if (AppDebug.isOpenDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        // 尽可能早, 推荐在 Application 中初始化
        ARouter.init(this)

        // Bugly
        Bugly.init(this)
        // xCrash 提供捕获 java 崩溃、native 崩溃和 ANR 的能力, 不需要 root 权限或任何系统权限
        xcrash.XCrash.init(this)
        // BlockCanary
        BlockCanaryKT.init(this)
        // 初始化 WebView 辅助类全局配置
        initializeWebViewBuilder()
    }

    // ==========
    // = 重写方法 =
    // ==========

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()

    // ============
    // = 初始化方法 =
    // ============

    /**
     * 初始化 WebView 辅助类全局配置
     */
    private fun initializeWebViewBuilder() {
        val builder: WebViewAssist.Builder = WebViewAssist.Builder()
            .setBuiltInZoomControls(true) // 显示内置缩放工具
            .setDisplayZoomControls(true) // 显示缩放工具
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
                    DevLogEngine.getEngine()?.apply {
                        d("WebViewAssist Builder onApply")
                    }
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