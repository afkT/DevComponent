package afkt_replace.core.property

import afkt_replace.core.app.AppContext
import afkt_replace.core.lib.base.BuildConfig
import afkt_replace.core.lib.engine.debug.DevDebugEngine
import android.app.Activity
import android.app.Application
import android.os.Bundle
import dev.base.activity.DevBaseActivity

/**
 * detail: Debug 悬浮窗处理
 * @author Ttt
 */
object FloatingDebug {

    /**
     * 初始化处理
     * @param appContext [AppContext]
     */
    fun initialize(appContext: AppContext) {
        if (BuildConfig.showDebugTools) {
            appContext.registerActivityLifecycleCallbacks(
                object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(
                        activity: Activity,
                        savedInstanceState: Bundle?
                    ) {
                    }

                    override fun onActivityStarted(activity: Activity) {
                        /**
                         * 不在 [onActivityCreated] 中调用是防止添加悬浮 View 给盖在 FrameLayout 其他布局下
                         * 并且在该方法内能够保证已经 addContentView
                         */
                        addDebugFloating(activity)
                    }

                    override fun onActivityResumed(activity: Activity) {
                    }

                    override fun onActivityPaused(activity: Activity) {
                    }

                    override fun onActivityStopped(activity: Activity) {
                    }

                    override fun onActivitySaveInstanceState(
                        activity: Activity,
                        outState: Bundle
                    ) {
                    }

                    override fun onActivityDestroyed(activity: Activity) {
                    }
                }
            )
        }
    }

    // =

    /**
     * 添加悬浮窗
     * @param activity Activity
     */
    private fun addDebugFloating(activity: Activity) {
        if (activity is DevBaseActivity) {
            activity.javaClass.canonicalName?.let { name ->
                // 属于启动页不添加
                if (name.endsWith("AppLauncherActivity")) {
                    return
                }
                // 属于欢迎页不添加
                if (name.contains(".splash.")) {
                    return
                }
                // 属于 Debug 辅助页面不添加
                if (name.contains(".DebugMainContainerActivity")) {
                    return
                }

                // 可自行添加过滤页面
                // xxxx

                // 连接 ( 显示 ) Debug 功能关联
                DevDebugEngine.getEngine()?.attachDebug(activity)
            }
        }
    }
}