package afkt_replace.module.user

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: User Module ( App Startup Initializer )
 * @author Ttt
 */
class UserInitializer : BaseInitializer<UserModule>() {

    override fun create(context: Context): UserModule {
        UserModule.instance.initialize(context)
        return UserModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}