package afkt_replace.core.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import dev.utils.LogPrintUtils

/**
 * detail: App 通用 Router
 * @author Ttt
 */
object AppRouter {

    // 日志 TAG
    private val TAG = AppRouter::class.java.simpleName

    /**
     * 构建 Router Postcard
     * @param path router Path
     * @param group router Group
     * @return [Postcard]
     */
    @Deprecated(
        "推荐使用 buildByUri 方法", ReplaceWith(
            "ARouter.getInstance().build(path, group)",
            "com.alibaba.android.arouter.launcher.ARouter"
        )
    )
    fun build(
        path: String,
        group: String
    ): Postcard {
        return ARouter.getInstance().build(path, group)
    }

    /**
     * 构建 Router Postcard
     * @param path router Path
     * @return [Postcard]
     */
    fun buildByUri(path: String): Postcard {
        // path 必须包含 group => /group/path
        val routerURI = Uri.parse(path)
        return ARouter.getInstance().build(routerURI)
    }

    /**
     * 通过 Router Postcard 获取 Intent
     * @param context [Context]
     * @param postcard [Postcard]
     * @return Intent?
     */
    fun routerIntent(
        context: Context,
        postcard: Postcard
    ): Intent? {
        return try {
            LogisticsCenter.completion(postcard)
            // postcard.context
            val intent = Intent(context, postcard.destination)
            intent.putExtras(postcard.extras)
            intent
        } catch (e: Exception) {
            LogPrintUtils.eTag(TAG, e, "routerIntent")
            null
        }
    }

    /**
     * 反射创建对应 service class type
     * @param service interface of service
     * @return instance of service
     */
    fun <T> navigation(service: Class<T>): T? {
        return ARouter.getInstance().navigation(service)
    }

    /**
     * 注入 service 和参数
     * @param clazz Any?
     */
    fun inject(clazz: Any?) {
        ARouter.getInstance().inject(clazz)
    }

    /**
     * 注入 service 和参数
     * @param clazz Any?
     */
    fun injectCatch(clazz: Any?) {
        try {
            inject(clazz)
        } catch (_: Exception) {
        }
    }
}

// ==========
// = 扩展函数 =
// ==========

/**
 * 通过 Router Postcard 获取 Intent
 * @param context [Context]
 * @return Intent?
 */
fun Postcard.routerIntent(context: Context): Intent? {
    return AppRouter.routerIntent(context, this)
}