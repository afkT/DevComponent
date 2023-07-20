package afkt_replace.core.project.bean.person

import afkt_replace.core.project.bean.base.TMDBHttpStatus
import afkt_replace.core.project.bean.base.TMDBPage
import dev.utils.common.StringUtils

/**
 * detail: 热门人物
 * @author Ttt
 */
class PopularPerson : TMDBPage<TMDBPerson>()

/**
 * TMDB 人物通用字段
 */
open class TMDBPerson : TMDBHttpStatus() {

    val id: Int = 0
    val name: String? = null
    val profile_path: String? = null
    val known_for: MutableList<KnownFor>? = null

    // ==========
    // = 快捷方法 =
    // ==========

    private var knownSplicing: String? = null

    fun knownSplicing(): String? {
        if (knownSplicing == null) {
            val builder = StringBuilder()
            known_for?.forEach {
                builder.append(it.title()).append(",")
            }
            if (builder.endsWith(",")) {
                builder.setLength(builder.length - 1)
            }
            knownSplicing = builder.toString()
        }
        return knownSplicing
    }
}

/**
 * 人物参演作品
 */
data class KnownFor(
    val id: Int,
    val media_type: String,
    val overview: String,
    val poster_path: String,
    val title: String,
    val name: String,
    val original_name: String,
    val original_title: String,
    val release_date: String,
    val first_air_date: String,
    val vote_average: Double,
) {

    fun title(): String? {
        return StringUtils.checkValues(title, name, original_title, original_name)
    }
}

/**
 * 人物详情
 */
data class PersonDetails(
    val id: Int,
    val name: String,
    val biography: String,
    val birthday: String,
    val gender: Int,
    val place_of_birth: String,
    val profile_path: String
)

/**
 * 人物参演作品
 */
data class PersonActing(
    val id: Int,
    val cast: List<KnownFor>
)