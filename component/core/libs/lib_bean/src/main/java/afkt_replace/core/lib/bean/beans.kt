package afkt_replace.core.lib.bean

import dev.utils.common.ColorUtils

/**
 * detail: APP 主题样式
 * @author Ttt
 */
open class ThemeStyle() {

    var value: String? = null

    var color: Int = 0

    constructor(
        value: String?,
    ) : this(
        value,
        ColorUtils.setAlphaDark(ColorUtils.getRandomColor(), 100)
    )

    constructor(
        value: String?,
        color: Int
    ) : this() {
        this.value = value
        this.color = color
    }
}