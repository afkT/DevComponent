package afkt_replace.standard.catalog

import java.io.File

/**
 * detail: 目录生成配置
 * @author Ttt
 */
object Config {

    // 当前目录
    val USER_DIR = System.getProperty("user.dir")

    // 项目路径
    val PROJECT_PATH = File(USER_DIR).absolutePath

    // ===============
    // = Interesting =
    // ===============

    // Interesting 文件名
    const val INTERESTING_DIR_NAME = "interesting"

    // Interesting 项目本地路径
    val INTERESTING_LOCAL_PATH = "$USER_DIR/$INTERESTING_DIR_NAME"

    // Interesting 文件目录注释
    val sInterestingCatalogMap: MutableMap<String, String> = HashMap()

    // =============
    // = Component =
    // =============

    // Component 文件名
    const val COMPONENT_DIR_NAME = "component"

    // Component 项目本地路径
    val COMPONENT_LOCAL_PATH = "$USER_DIR/$COMPONENT_DIR_NAME"

    // Component 文件目录注释
    val sComponentCatalogMap: MutableMap<String, String> = HashMap()

    // ====================
    // = Component - Core =
    // ====================

    // Core 文件名
    const val CORE_DIR_NAME = "core"

    // Core 文件本地路径
    val CORE_LOCAL_PATH = "$COMPONENT_LOCAL_PATH/$CORE_DIR_NAME"

    // Core 文件目录注释
    val sCoreCatalogMap: MutableMap<String, String> = HashMap()

    // Core About 注释
    val sCoreAboutMap: MutableMap<String, String> = HashMap()

    // Core 忽略目录
    val sCoreIgnoreCatalogs: MutableList<String> = ArrayList()

    // ===================
    // = Component - Libs =
    // ===================

    // Libs 文件名
    const val LIBS_DIR_NAME = "libs"

    // Libs 文件本地路径
    val LIBS_LOCAL_PATH = "$COMPONENT_LOCAL_PATH/$LIBS_DIR_NAME"

    // Libs 文件目录注释
    val sLibsCatalogMap: MutableMap<String, String> = HashMap()

    // Libs About 注释
    val sLibsAboutMap: MutableMap<String, String> = HashMap()

    // Libs 忽略目录
    val sLibsIgnoreCatalogs: List<String> = ArrayList()

    // ===============
    // = Application =
    // ===============

    // Application 文件名
    const val APP_DIR_NAME = "application"

    // Application 文件本地路径
    val APP_LOCAL_PATH = "$USER_DIR/$APP_DIR_NAME"

    // Application 文件目录注释
    val sAppCatalogMap: MutableMap<String, String> = HashMap()

    // ========================
    // = Application - Module =
    // ========================

    // Module 文件名
    const val MODULE_DIR_NAME = "module"

    // Module 文件本地路径
    val MODULE_LOCAL_PATH = "$APP_LOCAL_PATH/$MODULE_DIR_NAME"

    // Module 文件目录注释
    val sModuleCatalogMap: MutableMap<String, String> = HashMap()

    // Module About 注释
    val sModuleAboutMap: MutableMap<String, String> = HashMap()

    // Module 忽略目录
    val sModuleIgnoreCatalogs: List<String> = ArrayList()

    init {

        // ===============
        // = Interesting =
        // ===============

        sInterestingCatalogMap["interesting"] = "根目录"
        sInterestingCatalogMap[".DevReplace"] = "快捷替换组件化模板包名"
        sInterestingCatalogMap[".DevStandard"] = "项目规范统一检测、生成替换等"

        // =============
        // = Component =
        // =============

        sComponentCatalogMap["component"] = "根目录"
        sComponentCatalogMap[".core"] = "核心基础整合库"
        sComponentCatalogMap[".libs"] = "通用 Library、第三方库 clone 差异化修改"

        // ====================
        // = Component - Core =
        // ====================

        sCoreCatalogMap["core"] = "根目录"
        sCoreCatalogMap[".core"] = "核心基础整合库 ( 内部集成 core libs, 对外依赖该 module 即可 )"
        sCoreCatalogMap[".core_base_lib"] = "基础核心开发库依赖 ( libs 便捷依赖统一维护 )"
        sCoreCatalogMap[".libs"] = "具体功能拆分, 封装 lib"
        sCoreCatalogMap[".libs.lib_base"] = "基类相关 ( Activity、Application 等 )"
        sCoreCatalogMap[".libs.lib_base_split"] = "基础 ( 基类等 ) lib 拆包"
        sCoreCatalogMap[".libs.lib_bean"] = "通用实体类 ( module 实体类下沉 )"
        sCoreCatalogMap[".libs.lib_channel_flavors"] = "App 多渠道"
        sCoreCatalogMap[".libs.lib_config"] = "通用配置、常量信息"
        sCoreCatalogMap[".libs.lib_debug_assist"] =
            "Debug 编译辅助开发库 ( 提供切换环境、抓包数据可视化、调试按钮开关等辅助功能 )"
        sCoreCatalogMap[".libs.lib_engine"] =
            "通用 Engine ( 图片加载、日志、JSON、权限、资源选择、缓存 ) lib"
        sCoreCatalogMap[".libs.lib_environment"] = "通用环境配置切换库"
        sCoreCatalogMap[".libs.lib_mvvm"] =
            "MVVM 通用代码封装 ( 使用 MVVM module 必须 api 依赖如果使用 compileOnly 将会找不到 BindingAdapter 等 )"
        sCoreCatalogMap[".libs.lib_language"] = "通用多语言 lib"
        sCoreCatalogMap[".libs.lib_network"] = "网络相关 lib ( 网络请求、上传下载 )"
        sCoreCatalogMap[".libs.lib_property"] = "性能优化、检测 lib"
        sCoreCatalogMap[".libs.lib_receiver"] = "广播监听 ( 如网络状态、电量、屏幕解锁 ) 相关"
        sCoreCatalogMap[".libs.lib_router"] = "路由相关"
        sCoreCatalogMap[".libs.lib_ui"] = "统一 style、widget、ui 相关组件"
        sCoreCatalogMap[".libs.lib_upload"] = "通用上传库"
        sCoreCatalogMap[".libs.lib_ui_skin"] = "APP 主题、换肤相关控制"
        sCoreCatalogMap[".libs.lib_utils"] = "通用工具库"
        sCoreCatalogMap[".libs.lib_web"] = "WebView 相关"

        sCoreIgnoreCatalogs.add("core")
        sCoreIgnoreCatalogs.add("core_base_lib")

        sCoreAboutMap.putAll(sCoreCatalogMap)

        // ===================
        // = Component - Libs =
        // ===================

        sLibsCatalogMap["libs"] = "根目录"
        sLibsCatalogMap[".lib_splash_ads"] = "启动页广告 lib"
        sLibsCatalogMap[".lib_tmdb_ui"] = "TheMovieDB 通用 UI lib"

        sLibsAboutMap.putAll(sLibsCatalogMap)
        sLibsAboutMap[".lib_splash_ads"] = "启动页 module 依赖 lib，用于校验是否存在广告需要展示"
        sLibsAboutMap[".lib_tmdb_ui"] = "TheMovieDB 通用 UI、适配器依赖库"

        // ===============
        // = Application =
        // ===============

        sAppCatalogMap["application"] = "根目录"
        sAppCatalogMap[".app"] = "主体应用 ( APP 壳工程 )"
        sAppCatalogMap[".module"] = "具体功能模块 ( 可单独运行 )，被主体应用 ( 壳 ) 所依赖使用"

        // ========================
        // = Application - Module =
        // ========================

        sModuleCatalogMap["module"] = "根目录"
        sModuleCatalogMap[".module_template"] = "模板 Module ( 便于 copy )"
        sModuleCatalogMap[".module_main"] = "首页 Module"
        sModuleCatalogMap[".module_movie"] = "电影 Module"
        sModuleCatalogMap[".module_person"] = "人物 Module"
        sModuleCatalogMap[".module_splash"] = "启动页 ( 广告页、首次启动引导页 ) Module"
        sModuleCatalogMap[".module_tv"] = "剧集 Module"

        sModuleAboutMap.putAll(sModuleCatalogMap)
        sModuleAboutMap[".module_main"] = "首页 ( 底部 Button 导航 ) Module"
        sModuleAboutMap[".module_movie"] = "电影 Module"
        sModuleAboutMap[".module_person"] = "人物 Module"
        sModuleAboutMap[".module_splash"] = "启动页 ( 广告页、首次启动引导页 ) Module"
        sModuleAboutMap[".module_tv"] = "剧集 Module"
    }
}