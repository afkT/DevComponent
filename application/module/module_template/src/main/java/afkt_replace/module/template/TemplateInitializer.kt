package afkt_replace.module.template

import afkt_replace.core.BaseInitializer
import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Template Module ( App Startup Initializer )
 * @author Ttt
 */
class TemplateInitializer : BaseInitializer<TemplateModule>() {

    override fun create(context: Context): TemplateModule {
        TemplateModule.instance.initialize(context)
        return TemplateModule.instance
    }

    override fun dependencies_abs(): MutableList<Class<out Initializer<*>>> =
        Collections.emptyList()
}