package afkt_replace.core.router

import com.therouter.router.Navigator

/**
 * detail: Core Route Path
 * @author Ttt
 */
object CoreRouter {

    const val GROUP = "CORE"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    fun build(path: String): Navigator {
        return AppRouter.build(path)
    }
}