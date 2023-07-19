package afkt_replace.core.lib.bean.base

import dev.utils.common.StringUtils

/**
 * TMDB Http 请求状态
 * @property status_code Int
 * @property status_message String
 * @property success Boolean
 */
open class TMDBHttpStatus {
    val status_code: Int = 0
    val status_message: String? = null
    val success: Boolean = true
}

/**
 * TMDB Http 分页请求数据
 */
open class TMDBPage<T> : TMDBHttpStatus() {
    val page: Int = 1
    val results: MutableList<T>? = null
    val total_pages: Int = 1
    val total_results: Int = 1

    fun isFirstPage(): Boolean {
        return page <= 1
    }

    fun isLastPage(): Boolean {
        return page == total_pages
    }
}

// ==========
// = 通用数据 =
// ==========

/**
 * TMDB 通用字段
 */
open class TMDBCommon : TMDBHttpStatus() {

    val id: Int = 0
    val overview: String? = null
    val poster_path: String? = null
    val title: String? = null
    val name: String? = null
    val original_title: String? = null
    val original_name: String? = null
    val release_date: String? = null
    val first_air_date: String? = null
    val vote_average: Double = 0.0

    // ==========
    // = 快捷方法 =
    // ==========

    fun title(): String? {
        return StringUtils.checkValues(title, name, original_title, original_name)
    }

    fun firstDate(): String? {
        return StringUtils.checkValue(release_date, first_air_date)
    }
}