package afkt_replace.module.template

import afkt_replace.core.BaseModule
import android.content.Context

/**
 * detail: Template Module ( ContentProvider Initializer )
 * @author Ttt
 */
class TemplateModule private constructor() : BaseModule(TemplateModule::class.java.simpleName) {

    companion object {
        val instance: TemplateModule by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TemplateModule()
        }
    }

    /**
     * 初始化方法
     * @param context Context
     */
    fun initialize(context: Context) {
    }
}