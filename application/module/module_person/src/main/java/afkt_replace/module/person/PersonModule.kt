package afkt_replace.module.person

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: Person Module ( ContentProvider Initializer )
 * @author Ttt
 */
class PersonModule private constructor() : BaseModule(PersonModule::class.java.simpleName) {

    companion object {
        val instance: PersonModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PersonModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}