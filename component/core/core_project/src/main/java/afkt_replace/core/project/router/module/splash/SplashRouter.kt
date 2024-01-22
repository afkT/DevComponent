package afkt_replace.core.project.router.module.splash

import afkt_replace.core.router.AppRouter
import com.therouter.router.Navigator

/**
 * detail: Splash Module Router
 * @author Ttt
 */
object SplashRouter {

    const val GROUP = "splash"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_LAUNCHER = "/$GROUP/launcher"

    // ====================
    // = PATH - IProvider =
    // ====================

    // 对外公开 IProvider 通讯组件
    const val PATH_SPLASH_PROVIDER = "/$GROUP/provider"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Navigator {
        return AppRouter.build(path)
    }
}