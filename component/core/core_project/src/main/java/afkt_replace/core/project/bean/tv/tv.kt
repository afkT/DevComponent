package afkt_replace.core.project.bean.tv

import afkt_replace.core.project.bean.base.TMDBCommon
import afkt_replace.core.project.bean.base.TMDBPage
import dev.utils.common.StringUtils

/**
 * detail: 热门剧集
 * @author Ttt
 */
class PopularTv : TMDBPage<TMDBCommon>()

/**
 * 剧集详情
 */
data class TvDetails(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val poster_path: String,
) {

    fun title(): String? {
        return StringUtils.checkValues(name, original_name)
    }

    fun imagePath(): String? {
        return StringUtils.checkValues(backdrop_path, poster_path)
    }
}