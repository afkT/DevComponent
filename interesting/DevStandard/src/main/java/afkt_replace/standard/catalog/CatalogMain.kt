package afkt_replace.standard.catalog

/**
 * detail: 目录生成 Main 方法
 * @author Ttt
 */
internal object CatalogMain {

    @JvmStatic
    fun main(args: Array<String>) {

        // 生成 interesting 目录结构
        Utils.generateCatalog(
            Config.INTERESTING_LOCAL_PATH,
            Config.INTERESTING_DIR_NAME,
            Config.sInterestingCatalogMap,
            null, null, 0, false
        )

        // 生成 component 目录结构
        Utils.generateCatalog(
            Config.COMPONENT_LOCAL_PATH, Config.COMPONENT_DIR_NAME, Config.sComponentCatalogMap,
            null, null, 0, false
        )

        // 生成 component/core 目录结构
        Utils.generateCatalog(
            Config.CORE_LOCAL_PATH, Config.CORE_DIR_NAME, Config.sCoreCatalogMap,
            Config.sCoreAboutMap, Config.sCoreIgnoreCatalogs, 1, true
        )

        // 生成 component/libs 目录结构
        Utils.generateCatalog(
            Config.LIBS_LOCAL_PATH, Config.LIBS_DIR_NAME, Config.sLibsCatalogMap,
            Config.sLibsAboutMap, Config.sLibsIgnoreCatalogs, 0, true
        )

        // 生成 application 目录结构
        Utils.generateCatalog(
            Config.APP_LOCAL_PATH, Config.APP_DIR_NAME, Config.sAppCatalogMap,
            null, null, 0, false
        )

        // 生成 application/module 目录结构
        Utils.generateCatalog(
            Config.MODULE_LOCAL_PATH, Config.MODULE_DIR_NAME, Config.sModuleCatalogMap,
            Config.sModuleAboutMap, Config.sModuleIgnoreCatalogs, 0, true
        )
    }
}