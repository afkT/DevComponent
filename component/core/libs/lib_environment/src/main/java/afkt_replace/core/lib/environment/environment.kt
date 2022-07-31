package afkt_replace.core.lib.environment

import afkt_replace.core.lib.environment.EnvironmentTypeChecker.getEnvironment
import afkt_replace.core.lib.environment.EnvironmentTypeChecker.getEnvironmentByType
import afkt_replace.core.lib.environment.EnvironmentTypeChecker.getModuleBean
import afkt_replace.core.lib.environment.EnvironmentTypeChecker.getReleaseEnvironment
import afkt_replace.core.lib.environment.EnvironmentTypeChecker.innerGetBuildEnvironmentTypeBean
import afkt_replace.core.lib.environment.EnvironmentTypeChecker.setEnvironment
import dev.DevUtils
import dev.environment.DevEnvironment
import dev.environment.bean.EnvironmentBean
import dev.environment.bean.ModuleBean
import dev.environment.listener.OnEnvironmentChangeListener
import dev.utils.DevFinal
import dev.utils.app.share.SPUtils

/**
 * detail: 环境类型枚举类
 * @author Ttt
 * 环境类型尽量与 [HttpService] 相对应
 * 如需通过传入 [EnvironmentType] 切换环境且属于新的 alias
 * 则需要同步在该枚举内创建新的 type 内部通过
 * [innerGetBuildEnvironmentTypeBean]
 * 循环判断 alias 返回对应的 [EnvironmentBean]
 * 便于 [getEnvironmentByType] 方法切换环境
 */
enum class EnvironmentType(
    val type: Int,
    val alias: String
) {
    RELEASE(1, "生产环境"),

    DEBUG(2, "测试环境"),

    PRE_RELEASE(3, "预发布环境"),

    DEVELOPMENT(4, "开发环境"),

    ;
}

// ===========================
// = Environment Type 构建校验 =
// ===========================

/**
 * detail: Build Config Field Environment Type 构建校验
 * @author Ttt
 * [HttpService] 新增模块则同步需要再以下方法进行添加处理
 * [getModuleBean]、[getReleaseEnvironment]、[getEnvironment]、[setEnvironment]
 */
internal object EnvironmentTypeChecker {

    // 空实体类便于复用
    private val EMPTY_MODULE = ModuleBean("", "")
    private val EMPTY_ENVIRONMENT = EnvironmentBean("", "", "", EMPTY_MODULE)

    // 是否 Release 版本标记
    private fun isRelease() = BuildConfig.isRelease // DevEnvironment.isRelease()

//    // 是否 Release 版本标记 ( 用于标记 APK 是否 Release 发布版本 )
//    BuildConfig.isRelease
//    // DevEnvironment 环境库依赖构建版本标记
//    DevEnvironment.isRelease()

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 环境校验与重置
     * 需在 Application 内尽可能的早调用
     * 用于非 Release 版本下针对自动化构建工具支持环境切换处理
     */
    fun checker() {
        if (isRelease()) return

        // 非 Release 版本才进行处理
        val sp = SPUtils.getPreference(
            DevUtils.getContext(), BuildConfig.MODULE_NAME
        )
        // 上次构建时间
        val oldTime = sp?.getLong(DevFinal.STR.BUILD) ?: 0
        if (BuildConfig.BUILD_TIME > oldTime) {
            innerSetBuildEnvironment()
            sp?.put(DevFinal.STR.BUILD, BuildConfig.BUILD_TIME)
        }
    }

    /**
     * 添加模块环境改变触发事件
     * @param listener environment change listener
     * @return `true` success, `false` fail
     */
    fun addOnEnvironmentChangeListener(
        listener: OnEnvironmentChangeListener?
    ): Boolean {
        return DevEnvironment.addOnEnvironmentChangeListener(listener)
    }

    /**
     * 移除模块环境改变触发事件
     * @param listener environment change listener
     * @return `true` success, `false` fail
     */
    fun removeOnEnvironmentChangeListener(
        listener: OnEnvironmentChangeListener?
    ): Boolean {
        return DevEnvironment.removeOnEnvironmentChangeListener(listener)
    }

    /**
     * 清空模块环境改变触发事件
     * @return `true` success, `false` fail
     */
    fun clearOnEnvironmentChangeListener(): Boolean {
        return DevEnvironment.clearOnEnvironmentChangeListener()
    }

    /**
     * 获取指定 Module ModuleBean
     * @param moduleName module Name
     * @return [ModuleBean]
     */
    fun getModuleBean(moduleName: String): ModuleBean {
        return when (moduleName) {
            // 启动页 ( 广告页、首次启动引导页 ) 模块
            BuildConfig.ENV_MODULE_SPLASH -> {
                DevEnvironment.getSplashModule()
            }
            // 用户模块
            BuildConfig.ENV_MODULE_USER -> {
                DevEnvironment.getUserModule()
            }
            // 上传 lib
            BuildConfig.ENV_LIB_CORE_LIB_UPLOAD -> {
                DevEnvironment.getLibUploadModule()
            }
            // 玩 Android 模块
            BuildConfig.ENV_MODULE_WAN_ANDROID -> {
                DevEnvironment.getWanAndroidModule()
            }
            else -> {
                EMPTY_MODULE
            }
        }
    }

    /**
     * 获取指定 Module Release Environment Bean
     * @param moduleName module Name
     * @return [EnvironmentBean]
     */
    fun getReleaseEnvironment(moduleName: String): EnvironmentBean {
        return when (moduleName) {
            // 启动页 ( 广告页、首次启动引导页 ) 模块
            BuildConfig.ENV_MODULE_SPLASH -> {
                DevEnvironment.getSplashReleaseEnvironment()
            }
            // 用户模块
            BuildConfig.ENV_MODULE_USER -> {
                DevEnvironment.getUserReleaseEnvironment()
            }
            // 上传 lib
            BuildConfig.ENV_LIB_CORE_LIB_UPLOAD -> {
                DevEnvironment.getLibUploadReleaseEnvironment()
            }
            // 玩 Android 模块
            BuildConfig.ENV_MODULE_WAN_ANDROID -> {
                DevEnvironment.getWanAndroidReleaseEnvironment()
            }
            else -> {
                EMPTY_ENVIRONMENT
            }
        }
    }

    /**
     * 获取指定 Module Release Environment Bean Value
     * @param moduleName module Name
     * @return [EnvironmentBean.value]
     */
    fun getReleaseEnvironmentValue(moduleName: String): String {
        return getReleaseEnvironment(moduleName).value
    }

    /**
     * 获取指定 Module Selected Environment Bean
     * @param moduleName module Name
     * @return [EnvironmentBean]
     * 无需判断 [isRelease] gradle kapt 自动创建不同的类
     * Release DevEnvironment 直接返回 Release Environment
     */
    fun getEnvironment(moduleName: String): EnvironmentBean {
        val context = DevUtils.getContext()
        return when (moduleName) {
            // 启动页 ( 广告页、首次启动引导页 ) 模块
            BuildConfig.ENV_MODULE_SPLASH -> {
                DevEnvironment.getSplashEnvironment(context)
            }
            // 用户模块
            BuildConfig.ENV_MODULE_USER -> {
                DevEnvironment.getUserEnvironment(context)
            }
            // 上传 lib
            BuildConfig.ENV_LIB_CORE_LIB_UPLOAD -> {
                DevEnvironment.getLibUploadEnvironment(context)
            }
            // 玩 Android 模块
            BuildConfig.ENV_MODULE_WAN_ANDROID -> {
                DevEnvironment.getWanAndroidEnvironment(context)
            }
            else -> {
                EMPTY_ENVIRONMENT
            }
        }
    }

    /**
     * 获取指定 Module Selected Environment Bean Value
     * @param moduleName module Name
     * @return [EnvironmentBean.value]
     */
    fun getEnvironmentValue(moduleName: String): String {
        return getEnvironment(moduleName).value
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
        if (isRelease()) return false
        // 属于空实体类则不设置环境
        if (newEnvironment == EMPTY_ENVIRONMENT) {
            return false
        }
        val context = DevUtils.getContext()
        return when (moduleName) {
            // 启动页 ( 广告页、首次启动引导页 ) 模块
            BuildConfig.ENV_MODULE_SPLASH -> {
                DevEnvironment.setSplashEnvironment(context, newEnvironment)
            }
            // 用户模块
            BuildConfig.ENV_MODULE_USER -> {
                DevEnvironment.setUserEnvironment(context, newEnvironment)
            }
            // 上传 lib
            BuildConfig.ENV_LIB_CORE_LIB_UPLOAD -> {
                DevEnvironment.setLibUploadEnvironment(context, newEnvironment)
            }
            // 玩 Android 模块
            BuildConfig.ENV_MODULE_WAN_ANDROID -> {
                DevEnvironment.setWanAndroidEnvironment(context, newEnvironment)
            }
            else -> {
                false
            }
        }
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
        if (isRelease()) return false
        return setEnvironment(
            moduleName, getEnvironmentByType(
                moduleName, type
            )
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
        return innerGetBuildEnvironmentTypeBean(
            type, getModuleBean(moduleName)
        )
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 非 Release 版本下
     * 根据构建时间、environmentType 进行设置对应的环境
     */
    private fun innerSetBuildEnvironment() {
        val type = innerConvertBuildEnvironmentType()

        // =========================
        // = 设置每个模块构建的环境配置 =
        // =========================

        BuildConfig::class.java.declaredFields.forEach {
            it?.let { field ->
                if (field.type == String::class.java) {
                    val name = field.name
                    // 循环所有 ENV_ 字符串进行设置环境
                    if (name.startsWith("ENV_")) {
                        (field.get(name) as? String)?.let { moduleName ->
                            setEnvironment(moduleName, getEnvironmentByType(moduleName, type))
                        }
                    }
                }
            }
        }
    }

    /**
     * 通过构建值获取环境类型枚举
     * @return EnvironmentType
     */
    private fun innerConvertBuildEnvironmentType(): EnvironmentType {
        EnvironmentType.values().forEach {
            if (it.type == BuildConfig.environmentType) {
                return it
            }
        }
        return EnvironmentType.RELEASE
    }

    /**
     * 通过环境类型获取对应模块的环境信息
     * @param type EnvironmentType
     * @param module ModuleBean
     * @return EnvironmentBean
     */
    private fun innerGetBuildEnvironmentTypeBean(
        type: EnvironmentType,
        module: ModuleBean
    ): EnvironmentBean {
        module.environments.forEach {
            if (it.alias == type.alias) {
                return it
            }
        }
        return EMPTY_ENVIRONMENT
    }
}