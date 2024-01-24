package afkt_replace.core.lib.router.module.person

import afkt_replace.core.lib.router.module.AppRouter
import com.therouter.router.Navigator

/**
 * detail: Person Module Router
 * @author Ttt
 */
object PersonRouter {

    const val GROUP = "person"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/$GROUP/main"

    // ==================
    // = PATH - Popular =
    // ==================

    // 热门人物 Fragment
    const val PATH_POPULAR_FRAGMENT = "/$GROUP/popular/fragment"

    // =========================
    // = PATH - Person Details =
    // =========================

    // Person Details Activity
    const val PATH_PERSON_DETAILS_ACTIVITY = "/$GROUP/person/details/activity"

    // Person Details Fragment
    const val PATH_PERSON_DETAILS_FRAGMENT = "/$GROUP/person/details/fragment"

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