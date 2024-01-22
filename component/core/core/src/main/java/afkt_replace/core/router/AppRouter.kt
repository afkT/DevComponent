package afkt_replace.core.router

import android.content.Context
import android.content.Intent
import com.therouter.TheRouter
import com.therouter.router.Navigator
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
     * @return [Navigator]
     */
    fun build(path: String): Navigator {
        return TheRouter.build(path)
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
            return postcard.createIntent(context)
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
        return TheRouter.get(service)
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