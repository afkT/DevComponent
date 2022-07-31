package afkt_replace.module.wan_android

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: WanAndroid Module ( App Startup Initializer )
 * @author Ttt
 */
class WanAndroidInitializer : BaseInitializer<WanAndroidModule>() {

    override fun create(context: Context): WanAndroidModule {
        WanAndroidModule.instance.initialize(context)
        return WanAndroidModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}