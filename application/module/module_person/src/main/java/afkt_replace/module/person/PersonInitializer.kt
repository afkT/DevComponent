package afkt_replace.module.person

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Person Module ( App Startup Initializer )
 * @author Ttt
 */
class PersonInitializer : BaseInitializer<PersonModule>() {

    override fun create(context: Context): PersonModule {
        PersonModule.instance.initialize(context)
        return PersonModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}