package afkt_replace.module.movie

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Movie Module ( App Startup Initializer )
 * @author Ttt
 */
class MovieInitializer : BaseInitializer<MovieModule>() {

    override fun create(context: Context): MovieModule {
        MovieModule.instance.initialize(context)
        return MovieModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}