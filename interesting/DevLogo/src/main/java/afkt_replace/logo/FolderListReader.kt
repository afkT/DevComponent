package afkt_replace.logo

import dev.utils.common.FileUtils
import dev.utils.common.FileUtils.FileList
import dev.utils.common.StringUtils
import dev.utils.common.comparator.ComparatorUtils
import java.io.File

/**
 * detail: 文件目录结构读取
 * @author Ttt
 */
internal object FolderListReader {

    // ================
    // = 目录层级计算回调 =
    // ================

    /**
     * detail: 文件目录层级回调
     * @author Ttt
     */
    private interface CatalogCallback {
        /**
         * 回调通知
         * @param name       目录名
         * @param lineNumber 行数
         * @param classTag   Class TAG 标记
         */
        fun callback(
            name: String,
            lineNumber: Int,
            classTag: String
        )
    }

    // ==================
    // = 文件目录遍历实体类 =
    // ==================

    /**
     * 获取文件夹目录列表
     * @param path          文件路径
     * @param callback      目录回调通知
     * @param ignoreCatalog 忽略目录
     * @param layer         目录层级
     * @return 文件夹目录列表集合
     */
    private fun getFolderLists(
        path: String,
        callback: CatalogCallback?,
        ignoreCatalog: Array<String>?,
        layer: Int
    ): List<FileList> {
        return getFolderLists(path, callback, ignoreCatalog, layer, 0)
    }

    /**
     * 获取文件夹目录列表
     * @param path          文件路径
     * @param callback      目录回调通知
     * @param ignoreCatalog 忽略目录
     * @param layer         目录层级
     * @param curLayer      当前层级
     * @return 文件夹目录列表集合
     */
    private fun getFolderLists(
        path: String,
        callback: CatalogCallback?,
        ignoreCatalog: Array<String>?,
        layer: Int,
        curLayer: Int
    ): List<FileList> {
        // 当前层级大于想要的层级则 return
        if (curLayer > layer) return ArrayList()
        val lists: MutableList<FileList> = ArrayList()
        // 获取文件路径
        val baseFile = File(path)
        // 获取子文件
        val files = FileUtils.listFilesOrEmpty(baseFile)
        ComparatorUtils.sortWindowsExplorerFileSimpleComparatorAsc(files)
        for (file in files) {
            val name: String = file.name
            // 隐藏文件跳过
            if (file.isHidden || name.startsWith(".")) {
                continue
            }
            // 判断根目录是否需要忽略
            if (curLayer != 0 && ignoreCatalog != null
                && StringUtils.isContains(baseFile.name, *ignoreCatalog)
            ) {
                return lists
            }
            // 属于文件夹才处理
            if (file.isDirectory) {
                val catalog = FileList(
                    file, getFolderLists(
                        file.absolutePath,
                        callback, ignoreCatalog,
                        layer, curLayer + 1
                    )
                )
                lists.add(catalog)
                // 触发回调
                callback?.callback(name, curLayer + 1, "$name.$name")
            }
        }
        return lists
    }

    /**
     * 循环文件目录列表
     * @param result 存储目录列表
     * @param original 原始数据
     */
    private fun forFolderLists(
        result: MutableList<File>,
        original: List<FileList>
    ) {
        original.forEach {
            result.add(it.file)
            forFolderLists(result, it.subFiles)
        }
    }

    /**
     * 循环结果文件列表筛选出存在 res ( 如 drawable、mipmap ) 目录
     * @param result 存储筛选结果
     * @param folderArray 需要校验的目录
     */
    private fun forResultListByRes(
        result: MutableList<File>,
        folderArray: Array<String> = arrayOf(
            "/drawable", "/mipmap"
        )
    ): MutableList<File> {
        val lists: MutableList<File> = arrayListOf()
        result.forEach { file ->
            val absPath = file.absolutePath
            if (absPath.contains("/res") && !absPath.endsWith("/res")) {
                // 如果路径中包含指定数据则进行保存
                folderArray.forEach { value ->
                    if (absPath.contains(value)) {
                        lists.add(file)
                    }
                }
            }
        }
        return lists
    }

    /**
     * 循环 Res Result List 查询出包含指定名称的文件
     * @param resList Res Result List
     * @param nameArray 待校验的名称
     */
    private fun forResListByImage(
        resList: MutableList<File>,
        nameArray: Array<String> = arrayOf(
            "logo", "icon_launcher"
        )
    ): MutableList<File> {
        val lists: MutableList<File> = arrayListOf()
        val lowerArray = nameArray.clone()
        lowerArray.forEachIndexed { index, value ->
            lowerArray[index] = value.lowercase()
        }
        resList.forEach { res ->
            res.listFiles()?.forEach { file ->
                val name = file.name.lowercase()
                lowerArray.forEach { value ->
                    if (name.contains(value)) {
                        // 并且属于图片才保存
                        if (FileUtils.isImageFormats(file)) {
                            lists.add(file)
                        }
                    }
                }
            }
        }
        return lists
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 获取项目 Res 目录下属于指定名称的 logo 图片路径
     * @param nameArray 待校验的名称
     * @return logo 图片路径
     */
    fun getResLogoList(nameArray: Array<String>): MutableList<File> {
        // 当前目录
        val USER_DIR = System.getProperty("user.dir")
        // 项目路径
        val PROJECT_PATH = File(USER_DIR).absolutePath
        // 获取项目文件列表
        val projectList = getFolderLists(
            PROJECT_PATH, null, arrayOf(
                "build", "java"
            ), Int.MAX_VALUE
        )
        val result: MutableList<File> = arrayListOf()
        forFolderLists(result, projectList)
        // 循环结果文件列表筛选出存在 res ( 如 drawable、mipmap ) 目录
        val resList = forResultListByRes(result)
        // 循环 Res Result List 查询出包含指定名称的文件
        return forResListByImage(
            resList, nameArray
        )
    }
}