package afkt_replace.core.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.alibaba.android.arouter.core.LogisticsCenter
import com.therouter.router.Navigator
import com.therouter.TheRouter
import dev.utils.LogPrintUtils

/**
 * detail: App 通用 Router
 * @author Ttt
 */
object AppRouter {

    // 日志 TAG
    private val TAG = AppRouter::class.java.simpleName

    /**
     * 构建 Router Navigator
     * @param path router Path
     * @param group router Group
     * @return [Navigator]
     */
    @Deprecated(
        "推荐使用 buildByUri 方法", ReplaceWith(
            "TheRouter.build(path, group)",
            "com.therouter.TheRouter"
        )
    )
    fun build(
        path: String,
        group: String
    ): Navigator {
        return TheRouter.build(path, group)
    }

    /**
     * 构建 Router Navigator
     * @param path router Path
     * @return [Navigator]
     */
    fun buildByUri(path: String): Navigator {
        // path 必须包含 group => /group/path
        val routerURI = Uri.parse(path)
        return TheRouter.build(routerURI)
    }

    /**
     * 通过 Router Navigator 获取 Intent
     * @param context [Context]
     * @param postcard [Navigator]
     * @return Intent?
     */
    fun routerIntent(
        context: Context,
        postcard: Navigator
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
        return TheRouter.navigation(service)
    }

    /**
     * 注入 service 和参数
     * @param clazz Any?
     */
    fun inject(clazz: Any?) {
        TheRouter.inject(clazz)
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
 * 通过 Router Navigator 获取 Intent
 * @param context [Context]
 * @return Intent?
 */
fun Navigator.routerIntent(context: Context): Intent? {
    return AppRouter.routerIntent(context, this)
}