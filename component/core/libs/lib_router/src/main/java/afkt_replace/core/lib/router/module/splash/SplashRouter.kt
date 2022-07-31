package afkt_replace.core.lib.router.module.splash

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

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
    const val PATH_LAUNCHER = "/splash/launcher"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Postcard {
        return ARouter.getInstance().build(path, GROUP)
    }
}