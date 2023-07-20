package afkt_replace.module.tv

import afkt_replace.core.project.router.module.tv.TvNav

/**
 * detail: TvNav 跳转参数构建
 * @author Ttt
 */
object TvNavBuild {

    /**
     * 跳转 Tv Details Path
     * @receiver TMDBCommon
     * @return Postcard
     */
    internal fun afkt_replace.core.project.bean.base.TMDBCommon.routerTvDetails() {
        TvNav.buildTvDetails(
            id.toString(), title()
        ).navigation()
    }
}