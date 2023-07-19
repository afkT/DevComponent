package afkt_replace.core.lib.engine.debug

import androidx.fragment.app.FragmentActivity
import okhttp3.OkHttpClient

/**
 * detail: Debug 编译辅助开发 Engine 接口
 * @author Ttt
 */
interface IDebugEngine {

    /**
     * 设置 Debug 功能开关
     * @param display 是否显示 Debug 功能
     */
    fun setDebugFunction(display: Boolean)

    /**
     * 连接 ( 显示 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    fun attachDebug(activity: FragmentActivity?)

    /**
     * 分离 ( 隐藏 ) Debug 功能关联
     * @param activity 所属 Activity
     */
    fun detachDebug(activity: FragmentActivity?)

    /**
     * 添加抓包拦截器
     * @param builder    OkHttpClient Builder
     * @param moduleName 模块名 ( 要求唯一性 )
     */
    fun addInterceptor(
        builder: OkHttpClient.Builder,
        moduleName: String
    )
}