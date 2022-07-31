package afkt_replace.module.wan_android

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: WanAndroid Module ( ContentProvider Initializer )
 * @author Ttt
 */
class WanAndroidModule private constructor() : BaseModule(WanAndroidModule::class.java.simpleName) {

    companion object {
        val instance: WanAndroidModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WanAndroidModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}