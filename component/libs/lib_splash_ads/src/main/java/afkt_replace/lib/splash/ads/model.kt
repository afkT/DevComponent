package afkt_replace.lib.splash.ads

import afkt_replace.core.project.bean.splash.SplashAds
import com.jeremyliao.liveeventbus.core.LiveEvent

/**
 * detail: 广告位数据内部类
 * @author Ttt
 */
internal data class SplashAdsData(
    // yyyyMMdd
    val dateKey: String,
    // 广告数据源
    val list: List<SplashAds>
)

/**
 * detail: 广告位事件
 * @author Ttt
 */
data class SplashAdsEvent(
    val ads: SplashAds?,
    // 0 = 点击, 1 = 跳过
    val type: Int
) : LiveEvent