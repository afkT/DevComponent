package afkt_replace.core.lib.router.module.tv

import afkt_replace.core.lib.router.module.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: Tv Module Router
 * @author Ttt
 */
object TvRouter {

    const val GROUP = "tv"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/$GROUP/main"

    // ==================
    // = PATH - Popular =
    // ==================

    // 热门剧集 Fragment
    const val PATH_POPULAR_FRAGMENT = "/$GROUP/popular/fragment"

    // =====================
    // = PATH - Tv Details =
    // =====================

    // Tv Details Activity
    const val PATH_TV_DETAILS_ACTIVITY = "/$GROUP/tv/details/activity"

    // Tv Details Fragment
    const val PATH_TV_DETAILS_FRAGMENT = "/$GROUP/tv/details/fragment"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Postcard {
        return AppRouter.buildByUri(path)
    }
}