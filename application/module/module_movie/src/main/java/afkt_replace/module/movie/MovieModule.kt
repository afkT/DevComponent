package afkt_replace.module.movie

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: Movie Module ( ContentProvider Initializer )
 * @author Ttt
 */
class MovieModule private constructor() : BaseModule(MovieModule::class.java.simpleName) {

    companion object {
        val instance: MovieModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MovieModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}