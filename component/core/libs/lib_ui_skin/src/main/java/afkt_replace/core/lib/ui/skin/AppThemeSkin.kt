package afkt_replace.core.lib.ui.skin

import afkt_replace.core.lib.ui.skin.InitMerge.mergeInitByAppThemeRes
import androidx.lifecycle.MutableLiveData
import dev.utils.app.ResourcePluginUtils
import dev.utils.app.assist.ResourceAssist

/**
 * detail: APP 主题样式切换类
 * @author Ttt
 * 整个 APP 主题、换肤相关控制
 * 只需要下载 主题资源包.apk 后通过 [ResourcePluginUtils.invokeByAPKPath] 获取 [ResourceAssist]
 * 并进行赋值即可实现全局换肤
 */
class AppThemeSkin private constructor() {

    companion object {

        private val instance: AppThemeSkin by lazy { AppThemeSkin() }

        // ==============
        // = 对外公开方法 =
        // ==============

        fun skin(apkPath: String) {
            skin(ResourcePluginUtils.invokeByAPKPath(apkPath))
        }

        fun skin(resourcePlugin: ResourcePluginUtils) {
            instance.skin(resourcePlugin)
        }

        fun skin(appRes: AppThemeRes) {
            instance.initialize(appRes)
        }

        fun initialize(appRes: AppThemeRes) {
            instance.initialize(appRes)
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 通用赋值方法
     * @param globalRes 全局资源 AppTheme.xxx
     * @param currentRes 当前类资源值
     */
    internal fun <T> commonSetValue(
        globalRes: MutableLiveData<T>,
        currentRes: MutableLiveData<T>
    ) {
        currentRes.value?.let {
            globalRes.value = it
        }
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 资源换肤处理
     * @param resourcePlugin ResourcePluginUtils
     */
    private fun skin(resourcePlugin: ResourcePluginUtils) {
        resourcePlugin.resourceAssist?.let { assist ->
            // 以下为举例获取
            assist.getDrawable("launcher_module_bg")?.let {
                AppTheme._titleBarBackground.postValue(it)
            }
        }
    }

    /**
     * 初始化全局资源
     * @param appRes AppThemeRes
     * 当没有换肤资源时, 传入全局 AppThemeRes 进行赋值
     */
    private fun initialize(appRes: AppThemeRes) {
        mergeInitByAppThemeRes(appRes)
    }
}