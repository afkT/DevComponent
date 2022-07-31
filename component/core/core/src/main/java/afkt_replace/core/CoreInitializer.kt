package afkt_replace.core

import android.content.Context
import androidx.startup.Initializer
import java.util.*

/**
 * detail: Core Module ( App Startup Initializer )
 * @author Ttt
 * ContentProvider 的 onCreate() 方法是什么时候被调用的呢 ?
 * 它是介于 Application 的 attachBaseContext(Context) 和 onCreate() 之间所调用的
 * Application 的 attachBaseContext(Context) 方法被调用这就意味着 Application 的 Context 被初始化了
 */
class CoreInitializer : Initializer<CoreModule> {

    override fun create(context: Context): CoreModule {
        CoreModule.instance.initialize(context)
        return CoreModule.instance
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = Collections.emptyList()
}