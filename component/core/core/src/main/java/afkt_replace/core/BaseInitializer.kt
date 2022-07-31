package afkt_replace.core

import androidx.startup.Initializer

/**
 * detail: App Startup Initializer
 * @author Ttt
 * 其他 Library、Module 继承该类进行实现
 * 内部自动要求先进行初始化 [CoreInitializer] 模块
 */
abstract class BaseInitializer<T> : Initializer<T> {

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        // 需要在 CoreInitializer 初始化后才初始化该模块
        val lists: MutableList<Class<out Initializer<*>>> =
            mutableListOf(CoreInitializer::class.java)
        lists.addAll(dependencies_abs())
        return lists
    }

    abstract fun dependencies_abs(): MutableList<Class<out Initializer<*>>>
}