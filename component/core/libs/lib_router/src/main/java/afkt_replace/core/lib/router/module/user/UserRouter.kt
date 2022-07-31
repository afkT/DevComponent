package afkt_replace.core.lib.router.module.user

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 * detail: User Module Router
 * @author Ttt
 */
object UserRouter {

    const val GROUP = "user"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/user/main"

    // 对外公开 Fragment
    const val PATH_USER_FRAGMENT = "/user/user/fragment"

    // ====================
    // = PATH - IProvider =
    // ====================

    // 对外公开 Fragment
    const val PATH_USER_PROVIDER = "/user/provider"

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