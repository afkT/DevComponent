package afkt_replace.core.router.module.movie

import afkt_replace.core.config.ParamConst
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: 具体 navigation 类
 * @author Ttt
 * 不封装在对应的 Router 类中, 统一通过 Nav 类进行跳转、获取
 * 方便后续二次封装、统一编辑等处理
 */
object MovieNav {

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 GROUP 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 GROUP ( Module )
     */
    fun build(path: String): Postcard {
        return MovieRouter.build(path)
    }

    // ==========
    // = 跳转方法 =
    // ==========

    /**
     * 模块入口路由跳转
     */
    fun routerMain() {
        build(MovieRouter.PATH_MAIN).navigation()
    }

    // =================
    // = Movie Details =
    // =================

    /**
     * 构建 Movie Details Path Router
     * @param movieId movie id
     * @param title movie title
     * @return Postcard
     */
    fun buildMovieDetails(
        movieId: String,
        title: String?
    ): Postcard {
        return build(MovieRouter.PATH_MOVIE_DETAILS_ACTIVITY).apply {
            withString(ParamConst.MOVIE_ID, movieId)
            withString(ParamConst.TITLE, title)
        }
    }
}