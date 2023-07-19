package afkt_replace.module.tv

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Tv Module ( App Startup Initializer )
 * @author Ttt
 */
class TvInitializer : BaseInitializer<TvModule>() {

    override fun create(context: Context): TvModule {
        TvModule.instance.initialize(context)
        return TvModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}