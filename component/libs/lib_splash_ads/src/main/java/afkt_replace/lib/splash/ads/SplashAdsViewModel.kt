package afkt_replace.lib.splash.ads

import afkt_replace.core.base.controller.viewmodel.IntentDataViewModel
import afkt_replace.core.config.AppLibConfig
import afkt_replace.core.config.TypeConst
import afkt_replace.core.project.bean.splash.SplashAds
import afkt_replace.core.project.bean.splash.fromSplashAds
import afkt_replace.core.project.utils.tmdb.toTMDBImageSource
import android.graphics.Bitmap
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableLong
import androidx.lifecycle.LifecycleOwner
import com.jeremyliao.liveeventbus.LiveEventBus
import dev.assist.DevTimerAssist
import dev.base.DevSource
import dev.engine.image.listener.BitmapListener
import dev.mvvm.command.BindingConsumer

class SplashAdsViewModel : IntentDataViewModel() {

    private val splashAds: SplashAds by lazy {
        intentData.get().fromSplashAds()
    }

    val splashSource: DevSource by lazy {
        splashAds.resource.toTMDBImageSource()
    }

    val splashAnim = ObservableLong()

    val splashEnable = ObservableBoolean(false)

    val splashLoadListener = object : BitmapListener() {
        override fun onStart(source: DevSource?) {
        }

        override fun onResponse(
            source: DevSource?,
            value: Bitmap?
        ) {
            splashAnim.set(AppLibConfig.ANIM_DURATION_MILLIS)
            splashEnable.set(true)
        }

        override fun onFailure(
            source: DevSource?,
            throwable: Throwable?
        ) {
            onClickSkip.accept(null)
        }
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 点击广告
     */
    val onClickAds = object : BindingConsumer<View?> {
        override fun accept(value: View?) {
            postSplashAdsEvent(TypeConst.NORMAL)
        }
    }

    /**
     * 点击跳过
     */
    val onClickSkip = object : BindingConsumer<View?> {
        override fun accept(value: View?) {
            postSplashAdsEvent(TypeConst.SKIP)
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 发送广告广播
     */
    private fun postSplashAdsEvent(type: Int) {
        timer.stop()
        val ads = when (type) {
            TypeConst.NORMAL -> splashAds
            else -> null
        }
        LiveEventBus.get(SplashAdsEvent::class.java)
            .post(SplashAdsEvent(ads, type))
    }

    // ==========
    // = 生命周期 =
    // ==========

    private val timer: DevTimerAssist by lazy {
        DevTimerAssist(splashAds.previewTime, 300L)
            .setCallback { assist, number, end, duration ->
                if (end) onClickSkip.accept(null)
            }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        timer.start()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        timer.stop()
    }
}