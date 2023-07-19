package afkt_replace.core.lib.utils

import afkt_replace.core.lib.ui.R
import dev.base.DevSource
import dev.utils.common.StringUtils

/**
 * detail: TMDB 图片统一拼接、转换处理类
 * @author Ttt
 */

// 空白、错误占位图
private val ERROR_PLACEHOLDER = DevSource.create(R.drawable.global_error_placeholder)

// 图片前缀 - 原图
private const val IMAGE_PREFIX_ORIGINAL = "https://image.tmdb.org/t/p/original"

// ==============
// = 对外公开方法 =
// ==============

fun String?.toTMDBImageSource(): DevSource {
    if (StringUtils.isNotEmpty(this)) {
        return DevSource.create(
            IMAGE_PREFIX_ORIGINAL + this
        )
    }
    return ERROR_PLACEHOLDER
}

fun String?.toTMDBImageSourceEmpty(): DevSource? {
    if (StringUtils.isNotEmpty(this)) {
        return DevSource.create(
            IMAGE_PREFIX_ORIGINAL + this
        )
    }
    return null
}