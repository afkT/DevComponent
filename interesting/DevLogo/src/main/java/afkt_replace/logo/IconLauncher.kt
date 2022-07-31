package afkt_replace.logo

import dev.utils.common.FileUtils
import dev.utils.common.MapUtils
import java.io.File

/**
 * detail: Icon Launcher 各个目录管理
 * @author Ttt
 * icon_launcher.png
 * icon_launcher_round.png
 */
internal object IconLauncher {

    enum class SIZE(
        val width: Int,
        val height: Int
    ) {
        LDPI(36, 36),
        MDPI(48, 48),
        HDPI(72, 72),
        XHDPI(96, 96),
        XXHDPI(144, 144),
        XXXHDPI(192, 192)
    }

    private val TYPE_MIPMAP = "mipmap"

    private val TYPE_DRAWABLE = "drawable"

    private val FILE_ICON = "icon_launcher.png"

    private val FILE_ROUND_ICON = "icon_launcher_round.png"

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 是否属于 mipmap 类型
     * @param file File
     * @return `true` yes, `false` no
     */
    private fun isMipmap(file: File): Boolean {
        val absPath = FileUtils.getAbsolutePath(file)
        return absPath.contains("/$TYPE_MIPMAP")
    }

    /**
     * 是否属于 drawable 类型
     * @param file File
     * @return `true` yes, `false` no
     */
    private fun isDrawable(file: File): Boolean {
        val absPath = FileUtils.getAbsolutePath(file)
        return absPath.contains("/$TYPE_DRAWABLE")
    }

    /**
     * 根据 type - size 获取文件夹
     * @param type 文件夹类型, 例如: mipmap、drawable
     * @param size [SIZE]
     * @return 文件夹名
     */
    private fun getFolderByTypeSize(
        type: String,
        size: SIZE
    ): String {
        return "$type-${size.name}".lowercase()
    }

    /**
     * 根据文件获取文件夹
     * @param file File
     * @param size [SIZE]
     * @return 文件夹名
     */
    private fun getFolderBySize(
        file: File,
        size: SIZE
    ): String {
        if (isMipmap(file)) {
            return getFolderByTypeSize(TYPE_MIPMAP, size)
        } else if (isDrawable(file)) {
            return getFolderByTypeSize(TYPE_DRAWABLE, size)
        }
        return ""
    }

    /**
     * 根据 size 获取文件夹
     * @param file File
     * @param size [SIZE]
     * @return 文件夹名
     */
    private fun getFolder(
        file: File,
        size: SIZE
    ): String {
        // 例: application/app/src/main/res/mipmap-xxxhdpi/icon_launcher.png
        // 需要获取 application/app/src/main/res
        val resFile = file.parentFile.parentFile
        // 拼接文件夹结果为 application/app/src/main/res/mipmap-xxxhdpi
        return File(resFile, getFolderBySize(file, size)).absolutePath
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * detail: Icon 名过滤获取
     * @author Ttt
     */
    interface NameFilter {

        /**
         * 回调通知
         * @param file 文件路径
         * @param size [size]
         */
        fun callback(
            file: File,
            size: SIZE
        ): String
    }

    /**
     * detail: Icon 创建信息
     * @author Ttt
     */
    class ICON(
        // icon 大小
        val size: SIZE,
        // icon 文案
        val text: String,
        // 文件夹路径
        val folderPath: String
    ) {
        fun getIconName(): String {
            return FILE_ICON
        }

        fun getRoundIconName(): String {
            return FILE_ROUND_ICON
        }

        fun getIconFile(): File {
            return File(folderPath, getIconName())
        }

        fun getRoundIconFile(): File {
            return File(folderPath, getRoundIconName())
        }
    }

    /**
     * 转换待生成文件列表信息
     * @param resLogoList Res Logo File List
     * @param sizeArray Array<SIZE>
     * @param nameFilter [NameFilter]
     * @return List<ICON>
     */
    fun convertLists(
        resLogoList: List<File>,
        sizeArray: Array<SIZE>,
        nameFilter: NameFilter
    ): List<ICON> {
        val map: MutableMap<String, ICON> = mutableMapOf()
        sizeArray.forEach { size ->
            resLogoList.forEach { file ->
                // 获取文件夹路径作为 key
                val key = getFolder(file, size)
                // 不存在才处理
                if (!map.contains(key)) {
                    // 获取 icon 名
                    val iconName = nameFilter.callback(file, size)
                    map[key] = ICON(size, iconName, key)
                }
            }
        }
        return MapUtils.getValues(map)
    }
}