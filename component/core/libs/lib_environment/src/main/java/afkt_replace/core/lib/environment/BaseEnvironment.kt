package afkt_replace.core.lib.environment

import dev.environment.bean.EnvironmentBean
import dev.environment.bean.ModuleBean
import dev.environment.listener.OnEnvironmentChangeListener

/**
 * detail: APP 环境配置
 * @author Ttt
 * 该类用于给 Core Module AppEnvironment 类实现
 * 内部实现代码, 外部直接通过 AppEnvironment 进行调用无需关注内部实现逻辑
 * 减少该模块暴露
 * 如何使用:
 * 正常只需要使用 [getEnvironmentValue] 即可
 * 在 Release 编译下只会返回 Release 环境配置
 * 非 Release 编译下则会根据选中的环境进行返回
 */
interface BaseEnvironment {

    /**
     * 环境校验与重置
     * 需在 Application 内尽可能的早调用
     * 用于非 Release 版本下针对自动化构建工具支持环境切换处理
     */
    fun checker() {
        EnvironmentTypeChecker.checker()
    }

    /**
     * 添加模块环境改变触发事件
     * @param listener environment change listener
     * @return `true` success, `false` fail
     */
    fun addOnEnvironmentChangeListener(
        listener: OnEnvironmentChangeListener?
    ): Boolean {
        return EnvironmentTypeChecker.addOnEnvironmentChangeListener(listener)
    }

    /**
     * 移除模块环境改变触发事件
     * @param listener environment change listener
     * @return `true` success, `false` fail
     */
    fun removeOnEnvironmentChangeListener(
        listener: OnEnvironmentChangeListener?
    ): Boolean {
        return EnvironmentTypeChecker.removeOnEnvironmentChangeListener(listener)
    }

    /**
     * 清空模块环境改变触发事件
     * @return `true` success, `false` fail
     */
    fun clearOnEnvironmentChangeListener(): Boolean {
        return EnvironmentTypeChecker.clearOnEnvironmentChangeListener()
    }

    /**
     * 获取指定 Module ModuleBean
     * @param moduleName module Name
     * @return [ModuleBean]
     */
    fun getModuleBean(moduleName: String): ModuleBean {
        return EnvironmentTypeChecker.getModuleBean(moduleName)
    }

    /**
     * 获取指定 Module Release Environment Bean
     * @param moduleName module Name
     * @return [EnvironmentBean]
     */
    fun getReleaseEnvironment(moduleName: String): EnvironmentBean {
        return EnvironmentTypeChecker.getReleaseEnvironment(moduleName)
    }

    /**
     * 获取指定 Module Release Environment Bean Value
     * @param moduleName module Name
     * @return [EnvironmentBean.value]
     */
    fun getReleaseEnvironmentValue(moduleName: String): String {
        return EnvironmentTypeChecker.getReleaseEnvironmentValue(moduleName)
    }

    /**
     * 获取指定 Module Selected Environment Bean
     * @param moduleName module Name
     * @return [EnvironmentBean]
     */
    fun getEnvironment(moduleName: String): EnvironmentBean {
        return EnvironmentTypeChecker.getEnvironment(moduleName)
    }

    /**
     * 获取指定 Module Selected Environment Bean Value
     * @param moduleName module Name
     * @return [EnvironmentBean.value]
     */
    fun getEnvironmentValue(moduleName: String): String {
        return EnvironmentTypeChecker.getEnvironmentValue(moduleName)
    }

    /**
     * 设置指定 Module Environment Bean
     * @param moduleName module Name
     * @param newEnvironment environment bean
     * @return `true` success, `false` fail
     */
    fun setEnvironment(
        moduleName: String,
        newEnvironment: EnvironmentBean
    ): Boolean {
        return EnvironmentTypeChecker.setEnvironment(
            moduleName, newEnvironment
        )
    }

    /**
     * 设置指定 Module Environment Bean
     * @param moduleName module Name
     * @param type EnvironmentType
     * @return `true` success, `false` fail
     */
    fun setEnvironment(
        moduleName: String,
        type: EnvironmentType
    ): Boolean {
        return EnvironmentTypeChecker.setEnvironment(
            moduleName, type
        )
    }

    /**
     * 通过环境类型获取对应模块的 Environment Bean
     * @param moduleName module Name
     * @param type EnvironmentType
     * @return [EnvironmentBean]
     */
    fun getEnvironmentByType(
        moduleName: String,
        type: EnvironmentType
    ): EnvironmentBean {
        return EnvironmentTypeChecker.getEnvironmentByType(
            moduleName, type
        )
    }
}