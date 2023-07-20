package afkt_replace.lib.splash.ads

import afkt_replace.core.app.AppContext
import afkt_replace.core.project.bean.splash.NONE_ADS
import afkt_replace.core.project.bean.splash.SplashAds
import afkt_replace.core.project.router.module.splash.ISplashProvider
import afkt_replace.core.project.router.module.splash.SplashRouter
import afkt_replace.core.project.utils.tmdb.toTMDBImageSource
import android.content.Context
import android.graphics.Bitmap
import androidx.databinding.ObservableField
import com.bumptech.glide.request.RequestOptions
import com.tencent.mmkv.MMKV
import dev.engine.DevEngine
import dev.engine.image.ImageConfig
import dev.engine.keyvalue.DevKeyValueEngine
import dev.engine.keyvalue.MMKVKeyValueEngineImpl
import dev.expand.engine.image.getImageEngine
import dev.expand.engine.keyvalue.kv_getEntity
import dev.expand.engine.keyvalue.kv_putEntity
import dev.mvvm.utils.hi.hiif.hiIfNotNull
import dev.utils.DevFinal
import dev.utils.common.DateUtils
import dev.utils.common.RandomUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * detail: 启动页广告工具类
 * @author Ttt
 */
class SplashAdsUtils : ISplashProvider {

    private val ENGINE = SplashRouter.GROUP

    companion object {
        val instance: SplashAdsUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SplashAdsUtils()
        }
    }

    // =

    override fun init(context: Context?) {
        splashAdsOb.set(null)
        // 初始化 Splash 模块 KeyValue Engine
        DevKeyValueEngine.setEngine(
            ENGINE, MMKVKeyValueEngineImpl(
                DevEngine.createMMKVConfig(
                    mmkv = MMKV.mmkvWithID(ENGINE, MMKV.MULTI_PROCESS_MODE)
                )
            )
        )
    }

    // ===================
    // = ISplashProvider =
    // ===================

    override fun getAdsOb(): ObservableField<SplashAds> {
        return splashAdsOb
    }

    override fun queryAds() {
        readSplashAds()
    }

    override fun insertAds(list: List<SplashAds>): Boolean {
        return ENGINE.kv_putEntity(
            engine = ENGINE,
            value = SplashAdsData(
                dateKey = getNowDateKey(),
                list = list
            )
        )
    }

    // ==========
    // = 内部方法 =
    // ==========

    // 默认广告监听
    private val splashAdsOb = ObservableField<SplashAds>()

    /**
     * 获取当前时间 key
     * @return yyyyMMdd
     */
    private fun getNowDateKey(): String {
        return DateUtils.getDateNow(DevFinal.TIME.yyyyMMdd)
    }

    /**
     * 读取启动页广告展示信息
     */
    @OptIn(DelicateCoroutinesApi::class)
    private fun readSplashAds() {
        // 读取数据源
        ENGINE.kv_getEntity<SplashAdsData>(
            engine = ENGINE,
            typeOfT = SplashAdsData::class.java
        ).hiIfNotNull({
            if (getNowDateKey() == it.dateKey) {
                GlobalScope.launch {
                    checkerSplashAdsResource(it.list.toMutableList())
                }
            } else {
                splashAdsOb.set(NONE_ADS)
            }
        }, {
            splashAdsOb.set(NONE_ADS)
        })
    }

    /**
     * 检查广告资源
     * @param list 广告资源
     */
    private fun checkerSplashAdsResource(list: MutableList<SplashAds>) {
        if (list.isEmpty()) {
            splashAdsOb.set(NONE_ADS)
            return
        }
        val index = RandomUtils.getRandom(list.size)
        val splashAds = list[index]
        val source = splashAds.resource.toTMDBImageSource()
//        val file: File? = try {
//            Glide.with(AppContext.context()).downloadOnly().load(
//                source.mUrl
//            ).apply(RequestOptions().onlyRetrieveFromCache(true))
//                .submit().get()
//        } catch (_: Exception) {
//            null
//        }
//        if (file?.exists() == true) {
//            splashAdsOb.set(splashAds)
//            return
//        }
        val bitmap: Bitmap? = try {
            ENGINE.getImageEngine()?.loadBitmapThrows(
                AppContext.context(), source, mConfig
            )
        } catch (_: Exception) {
            null
        }
        if (bitmap != null) {
            splashAdsOb.set(splashAds)
            return
        }
        list.removeAt(index)
        checkerSplashAdsResource(list)
    }

    private val mConfig = ImageConfig.create().apply {
        setOptions(RequestOptions().onlyRetrieveFromCache(true))
    }
}