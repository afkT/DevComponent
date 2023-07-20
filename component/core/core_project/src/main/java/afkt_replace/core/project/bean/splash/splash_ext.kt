package afkt_replace.core.project.bean.splash

import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.project.bean.base.TMDBCommon
import dev.expand.engine.json.fromJson

// 无广告实体类
val NONE_ADS = SplashAds(
    previewTime = 0L,
    resId = "",
    resource = "",
    title = ""
)

// ==========
// = 扩展函数 =
// ==========

fun IntentData?.fromSplashAds(): SplashAds {
    return this?.getAds()?.fromJson(
        classOfT = SplashAds::class.java
    ) ?: NONE_ADS
}

fun List<TMDBCommon>?.toSplashAdsList(): List<SplashAds> {
    val list = mutableListOf<SplashAds>()
    this?.forEach {
        if (it.poster_path?.isNotBlank() == true) {
            list.add(
                SplashAds(
                    previewTime = 3000L,
                    resId = it.id.toString(),
                    resource = it.poster_path,
                    title = it.title() ?: ""
                )
            )
        }
    }
    return list
}