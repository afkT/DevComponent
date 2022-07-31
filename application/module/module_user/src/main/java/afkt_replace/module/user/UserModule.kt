package afkt_replace.module.user

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: User Module ( ContentProvider Initializer )
 * @author Ttt
 */
class UserModule private constructor() : BaseModule(UserModule::class.java.simpleName) {

    companion object {
        val instance: UserModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}