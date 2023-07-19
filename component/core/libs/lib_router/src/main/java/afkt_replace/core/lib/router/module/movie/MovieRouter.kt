package afkt_replace.core.lib.router.module.movie

import afkt_replace.core.lib.router.module.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: Movie Module Router
 * @author Ttt
 */
object MovieRouter {

    const val GROUP = "movie"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/$GROUP/main"

    // ==================
    // = PATH - Popular =
    // ==================

    // 热门电影 Fragment
    const val PATH_POPULAR_FRAGMENT = "/$GROUP/popular/fragment"

    // ========================
    // = PATH - Movie Details =
    // ========================

    // Movie Details Activity
    const val PATH_MOVIE_DETAILS_ACTIVITY = "/$GROUP/movie/details/activity"

    // Movie Details Fragment
    const val PATH_MOVIE_DETAILS_FRAGMENT = "/$GROUP/movie/details/fragment"

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