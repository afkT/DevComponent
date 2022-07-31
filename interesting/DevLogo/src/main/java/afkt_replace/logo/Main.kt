package afkt_replace.logo

import java.io.File

internal object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val resLogoList = FolderListReader.getResLogoList(
            arrayOf("icon_launcher")
        )
        val iconList = IconLauncher.convertLists(
            resLogoList, arrayOf(
                IconLauncher.SIZE.XHDPI,
                IconLauncher.SIZE.XXHDPI,
                IconLauncher.SIZE.XXXHDPI,
            ), innerNameFilter
        )
        IconGenerate.generate(iconList)

        val debug = ""
    }

    private val innerNameFilter = object : IconLauncher.NameFilter {
        override fun callback(
            file: File,
            size: IconLauncher.SIZE
        ): String {
            val absPath = file.absolutePath
            // 越详细越准确
            if (absPath.contains("application/app/src/app_default/res")) {
                return "App"
            } else if (absPath.contains("application/app/src/hua_wei/res")) {
                return "Huawei"
            } else if (absPath.contains("application/app/src/xiao_mi/res")) {
                return "Xiaomi"
            } else if (absPath.contains("application/module/module_main")) {
                return "Main"
            } else if (absPath.contains("application/module/module_splash")) {
                return "Splash"
            } else if (absPath.contains("application/module/module_template")) {
                return "Template"
            } else if (absPath.contains("application/module/module_user")) {
                return "User"
            } else if (absPath.contains("application/module/module_wanandroid")) {
                return "WanAndroid"
            }
            return ""
        }
    }
}