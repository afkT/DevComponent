package afkt_replace.core.project.router.module.tv

import afkt_replace.core.config.ParamConst
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: 具体 navigation 类
 * @author Ttt
 * 不封装在对应的 Router 类中, 统一通过 Nav 类进行跳转、获取
 * 方便后续二次封装、统一编辑等处理
 */
object TvNav {

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 GROUP 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 GROUP ( Module )
     */
    fun build(path: String): Postcard {
        return TvRouter.build(path)
    }

    // ==========
    // = 跳转方法 =
    // ==========

    /**
     * 模块入口路由跳转
     */
    fun routerMain() {
        build(TvRouter.PATH_MAIN).navigation()
    }

    // ==============
    // = Tv Details =
    // ==============

    /**
     * 构建 Tv Details Path Router
     * @param tvId tv id
     * @param title tv title
     * @return Postcard
     */
    fun buildTvDetails(
        tvId: String,
        title: String?
    ): Postcard {
        return build(TvRouter.PATH_TV_DETAILS_ACTIVITY).apply {
            withString(ParamConst.TV_ID, tvId)
            withString(ParamConst.TITLE, title)
        }
    }
}