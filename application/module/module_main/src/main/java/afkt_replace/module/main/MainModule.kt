package afkt_replace.module.main

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: Main Module ( ContentProvider Initializer )
 * @author Ttt
 */
class MainModule private constructor() : BaseModule(MainModule::class.java.simpleName) {

    companion object {
        val instance: MainModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MainModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}