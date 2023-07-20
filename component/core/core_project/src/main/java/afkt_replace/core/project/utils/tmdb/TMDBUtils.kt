package afkt_replace.core.project.utils.tmdb

import afkt_replace.core.project.R
import dev.utils.app.ResourceUtils

/**
 * detail: TMDB 工具类
 * @author Ttt
 */
object TMDBUtils {

    /**
     * 通过投票平均分获取进度条颜色
     * @param value 投票平均分
     */
    @JvmStatic
    fun getVoteAverageColor(value: Double? = 0.0): Int {
        val progress = value ?: 0.0
        return if (progress <= 3) {
            ResourceUtils.getColor(R.color.color_e94320)
        } else if (progress > 3.3 && progress < 7) {
            ResourceUtils.getColor(R.color.color_f1ec19)
        } else {
            ResourceUtils.getColor(R.color.color_2dd93e)
        }
    }
}