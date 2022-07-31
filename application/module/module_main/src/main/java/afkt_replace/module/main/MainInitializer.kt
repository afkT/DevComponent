package afkt_replace.module.main

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Main Module ( App Startup Initializer )
 * @author Ttt
 */
class MainInitializer : BaseInitializer<MainModule>() {

    override fun create(context: Context): MainModule {
        MainModule.instance.initialize(context)
        return MainModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}