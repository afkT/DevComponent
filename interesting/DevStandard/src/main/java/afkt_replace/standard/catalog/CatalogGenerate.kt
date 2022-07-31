package afkt_replace.standard.catalog

import dev.utils.common.CollectionUtils
import dev.utils.common.FileUtils
import dev.utils.common.FileUtils.FileList
import dev.utils.common.StringUtils
import dev.utils.common.comparator.ComparatorUtils
import java.io.File

/**
 * detail: 文件目录结构生成
 * @author Ttt
 */
internal object CatalogGenerate {

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

    // ==========
    // = 内部方法 =
    // ==========

    // 目录信息最大长度
    private var sMaxLength = 0

    /**
     * 重置目录信息最大长度
     */
    private fun resetMaxLength() {
        sMaxLength = 0
    }

    /**
     * 计算目录最大长度
     * @param name       目录名
     * @param lineNumber 行数
     */
    private fun calculateMaxLength(
        name: String,
        lineNumber: Int
    ) {
        val builder = StringBuilder() // 添加目录
        builder.append(createCatalog(name, lineNumber))
        val length = builder.length
        // 判断长度 => 大于最大长度, 则重新设置
        if (length + 6 >= sMaxLength) {
            sMaxLength = length + 12 // 6
        }
    }

    /**
     * 创建目录信息
     * @param name       目录名
     * @param lineNumber 行数
     * @return 目录信息
     */
    private fun createCatalog(
        name: String,
        lineNumber: Int
    ): String {
        val builder = StringBuilder() // 添加空格
        builder.append(StringUtils.appendSpace(lineNumber * 3))
        builder.append("- ").append(name) // 打印目录
        return builder.toString()
    }

    /**
     * 创建目录行
     * @param name       目录名
     * @param lineNumber 行数
     * @param classTag   Class TAG 标记
     * @param mapCatalog 对应目录注释
     * @return 目录行信息
     */
    private fun createCatalogLine(
        name: String,
        lineNumber: Int,
        classTag: String,
        mapCatalog: Map<String, String>
    ): String {
        val builder = StringBuilder() // 添加目录
        builder.append(createCatalog(name, lineNumber))
        // 设置间隔长度
        builder.append(StringUtils.appendSpace(sMaxLength - builder.length))
        // 添加 间隔 |
        builder.append("| ").append(mapCatalog[classTag])
        return builder.toString()
    }

    /**
     * 递归目录拼接目录列表信息
     * @param builder    拼接 Builder
     * @param lists      目录列表
     * @param lineNumber 行数
     * @param classTag   Class TAG 标记
     * @param mapCatalog 对应目录注释
     */
    private fun forCatalog(
        builder: StringBuilder,
        lists: List<FileList>,
        lineNumber: Int,
        classTag: String,
        mapCatalog: Map<String, String>
    ) {
        for (element in lists) {
            // 获取目录
            val catalog = element
            // 获取目录名
            val name = catalog.file.name
            // 进行换行
            builder.append("\n")
            // 添加目录行
            builder.append(
                createCatalogLine(
                    name, lineNumber, "$classTag.$name", mapCatalog
                )
            )
            // 判断是否存在子文件夹
            if (catalog.subFiles.size != 0) {
                forCatalog(
                    builder, catalog.subFiles,
                    lineNumber + 1,
                    "$classTag.$name",
                    mapCatalog
                )
            }
        }
    }

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 生成目录信息
     * @param path              文件路径
     * @param dirName           文件名
     * @param mapCatalog        对应目录注释
     * @param listIgnoreCatalog 忽略目录
     * @param layer             目录层级
     * @return 目录信息
     */
    fun generate(
        path: String,
        dirName: String,
        mapCatalog: Map<String, String>,
        listIgnoreCatalog: List<String>?,
        layer: Int
    ): String {
        // 重置目录信息最大长度
        resetMaxLength()
        // 拼接信息
        val builder = java.lang.StringBuilder()
        // 获取文件夹列表
        val lists = getFolderLists(path, object : CatalogCallback {
            override fun callback(
                name: String,
                lineNumber: Int,
                classTag: String
            ) {
                // 计算目录最大长度
                calculateMaxLength(name, lineNumber)
            }
        }, CollectionUtils.toArrayT(listIgnoreCatalog), layer)
        // 默认头部
        val head = "- $dirName"
        builder.append("```\n")
        // 增加根目录
        builder.append(head)
            .append(StringUtils.appendSpace(sMaxLength - head.length)).append("| ")
            .append(mapCatalog[dirName])
        // 递归循环目录
        forCatalog(builder, lists, 1, "", mapCatalog)
        builder.append("\n```\n")
        return builder.toString()
    }
}