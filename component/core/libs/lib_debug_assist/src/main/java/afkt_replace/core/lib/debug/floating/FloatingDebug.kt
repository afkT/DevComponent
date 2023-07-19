package afkt_replace.core.lib.debug.floating

import afkt_replace.core.lib.engine.debug.IDebugEngine
import androidx.fragment.app.FragmentActivity
import dev.DevHttpCapture
import dev.base.DevVariableExt
import okhttp3.OkHttpClient

/**
 * detail: 悬浮窗 Debug Engine 实现
 * @author Ttt
 */
internal class FloatingDebug : IDebugEngine {

    companion object {

        val instance: FloatingDebug by lazy { FloatingDebug() }
    }

    // ================
    // = IDebugEngine =
    // ================

    /**
     * 设置 Debug 功能开关
     * @param display 是否显示 Debug 功能
     */
    override fun setDebugFunction(display: Boolean) {
        FloatingUtils.instance.isNeedsAdd = display
        // 隐藏删除全部
        if (!display) {
            FloatingUtils.instance.removeAllFloatingView()
        }
    }

    /**
     * 连接 ( 显示 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    override fun attachDebug(activity: FragmentActivity?) {
        activity?.let { act ->
            mVariable.getVariableValue(act)?.let { value ->
                FloatingUtils.instance.addFloatingView(value)
                return
            }
        }
    }

    /**
     * 分离 ( 隐藏 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    override fun detachDebug(activity: FragmentActivity?) {
        activity?.let { act ->
            mVariable.variable.getVariableValue(act)?.let { value ->
                FloatingUtils.instance.removeFloatingView(value)
                mVariable.variable.removeVariable(act)
            }
        }
    }

    /**
     * 添加抓包拦截器
     * @param builder    OkHttpClient Builder
     * @param moduleName 模块名 ( 要求唯一性 )
     */
    override fun addInterceptor(
        builder: OkHttpClient.Builder,
        moduleName: String
    ) {
        DevHttpCapture.addInterceptor(builder, moduleName)
    }

    // ==========
    // = 内部实现 =
    // ==========

    // 变量操作基类扩展类
    private val mVariable: DevVariableExt<FragmentActivity, FloatingLifecycle, FragmentActivity> by lazy {
        DevVariableExt<FragmentActivity, FloatingLifecycle, FragmentActivity>().apply {
            setCreator { key, param ->
                key?.let {
                    FloatingLifecycle(key)
                }
            }
        }
    }
}