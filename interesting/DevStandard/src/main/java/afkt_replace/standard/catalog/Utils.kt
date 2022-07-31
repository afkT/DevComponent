package afkt_replace.standard.catalog

import dev.utils.DevFinal
import dev.utils.common.FileUtils
import dev.utils.common.StringUtils
import dev.utils.common.comparator.ComparatorUtils
import java.io.File
import java.io.UnsupportedEncodingException

/**
 * detail: 内部工具类
 * @author Ttt
 */
object Utils {

    /**
     * 生成目录文件
     * @param path              文件路径
     * @param dirName           文件名
     * @param mapCatalog        对应目录注释
     * @param mapAbout          About 注释
     * @param listIgnoreCatalog 忽略目录
     * @param layer             目录层级
     */
    fun generateCatalog(
        path: String,
        dirName: String,
        mapCatalog: Map<String, String>,
        mapAbout: Map<String, String>?,
        listIgnoreCatalog: List<String>?,
        layer: Int,
        generateDependenciesCatalog: Boolean
    ) {
        val catalog = CatalogGenerate.generate(path, dirName, mapCatalog, listIgnoreCatalog, layer)
        val builder = StringBuilder()
        // 插入文档头部内容
        insertHeadREADME(builder, path)
        // 文档内容
        builder.append(DevFinal.SYMBOL.NEW_LINE)
            .append("## 目录结构")
            .append(DevFinal.SYMBOL.NEW_LINE_X2)
            .append(catalog)
        // 插入文档尾部内容
        insertTailREADME(builder, path)
        val readme = StringUtils.clearEndsWith(builder.toString(), "\n")
        try {
            FileUtils.saveFile(
                File(path, "README.md"),
                readme.toByteArray(charset(DevFinal.ENCODE.UTF_8))
            )
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        if (generateDependenciesCatalog) {
            generateDependenciesCatalog(path, "", mapAbout)
        }
    }

    /**
     * 生成依赖目录文件
     * @param path     文件路径
     * @param dirName  文件名
     * @param mapAbout About 注释
     */
    private fun generateDependenciesCatalog(
        path: String,
        dirName: String,
        mapAbout: Map<String, String>?
    ) {
        val root = File(path, dirName.replace("\\.".toRegex(), "//"))
        val names = FileUtils.listOrEmpty(root)
        ComparatorUtils.sortWindowsExplorerStringSimpleComparatorAsc(names)
        for (name in names) {
            val catalog = "$dirName.$name"
            val catalogPath = path + catalog.replace("\\.".toRegex(), "//") + File.separator

            mapAbout?.let { map ->
                if (map.containsKey(catalog)) {
                    val gradleFile = File(catalogPath + "build.gradle")
                    // 文件存在则进行生成
                    if (gradleFile.exists()) {
                        generateDependenciesREADME(catalogPath, map[catalog])
                    } else {
                        var dir = "."
                        if (StringUtils.isNotEmpty(dirName)) {
                            dir += "$dirName."
                            dir = "." + StringUtils.clearStartsWith(dir, ".")
                        }
                        generateDependenciesCatalog(path, dir + name, map)
                    }
                }
            }
        }
    }

    /**
     * 生成依赖目录文件
     * @param path  文件路径
     * @param about module 功能
     */
    private fun generateDependenciesREADME(
        path: String,
        about: String?
    ) {
        // 读取文件
        var content: String? = null
        var dependencies: String? = null
        try {
            content = String(
                FileUtils.readFileBytes(File(path, "build.gradle")),
                charset(DevFinal.ENCODE.UTF_8)
            )
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        content?.let {
            if (it.indexOf("dependencies") != -1) {
                dependencies = it.substring(it.indexOf("dependencies"))
            }
        }
        val builder = StringBuilder()
        builder.append(DevFinal.SYMBOL.NEW_LINE)
            .append("# About")
            .append(DevFinal.SYMBOL.NEW_LINE_X2)
            .append(about)
        if (StringUtils.isNotEmpty(dependencies)) {
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                .append("# 依赖信息")
                .append(DevFinal.SYMBOL.NEW_LINE_X2)
                .append("```groovy")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append(dependencies)
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("```")
        }
        // 保存 AndroidManifest README
        generateAndroidManifestREADME(builder, path)
        // 保存 main 目录信息
        generateMainCatalogREADME(builder, path)
        // 插入文档尾部内容
        insertModuleTailREADME(builder, path)

        val readme = builder.toString()
        try {
            FileUtils.saveFile(
                File(path, "README.md"),
                readme.toByteArray(charset(DevFinal.ENCODE.UTF_8))
            )
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    /**
     * 生成 AndroidManifest 信息
     * @param builder [StringBuilder]
     * @param path    文件路径
     */
    private fun generateAndroidManifestREADME(
        builder: StringBuilder,
        path: String
    ) {
        val manifestFile = File(path, "src/main/AndroidManifest.xml")
        if (manifestFile.exists()) {
            // 读取文件
            var content: String? = null
            try {
                content = String(
                    FileUtils.readFileBytes(manifestFile),
                    charset(DevFinal.ENCODE.UTF_8)
                )
            } catch (e: UnsupportedEncodingException) {
            }
            if (StringUtils.isNotEmpty(content)) {
                builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append("# AndroidManifest")
                    .append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append("```xml")
                    .append(DevFinal.SYMBOL.NEW_LINE)
                    .append(content)
                    .append(DevFinal.SYMBOL.NEW_LINE)
                    .append("```")
            }
        }
    }

    /**
     * 生成 main 目录信息
     * @param builder [StringBuilder]
     * @param path    文件路径
     */
    private fun generateMainCatalogREADME(
        builder: StringBuilder,
        path: String
    ) {
        val javaFile = File(path, "src/main/java")
        if (javaFile.exists()) {
            var catalog = CatalogGenerate.generate(
                javaFile.absolutePath, "java",
                HashMap(), ArrayList(), Int.MAX_VALUE
            )
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replace("\\| null".toRegex(), "")
                builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append("# main/java 目录结构")
                    .append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append(catalog)
            }
        }

        val resFile = File(path, "src/main/res")
        if (resFile.exists()) {
            var catalog = CatalogGenerate.generate(
                resFile.absolutePath, "res",
                HashMap(), ArrayList(), Int.MAX_VALUE
            )
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replace("\\| null".toRegex(), "")
                builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append("# main/res 目录结构")
                    .append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append(catalog)
            }
        }

        val mainFile = File(path, "src/main")
        val mainFiles = FileUtils.listFilesOrEmpty(mainFile)
        ComparatorUtils.sortWindowsExplorerFileSimpleComparatorAsc(mainFiles)
        if (mainFiles != null) {
            for (mrFile in mainFiles) {
                val mainResFileName = mrFile.name
                if (mrFile.isDirectory && StringUtils.isStartsWith(mainResFileName, "res-")) {
                    val mainResFile = File(path, "src/main/$mainResFileName")
                    if (mainResFile.exists()) {
                        var catalog = CatalogGenerate.generate(
                            mainResFile.absolutePath,
                            mainResFileName, HashMap(),
                            ArrayList(), Int.MAX_VALUE
                        )
                        if (StringUtils.isNotEmpty(catalog)) {
                            catalog = catalog.replace("\\| null".toRegex(), "")
                            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                                .append("# main/").append(mainResFileName).append(" 目录结构")
                                .append(DevFinal.SYMBOL.NEW_LINE_X2)
                                .append(catalog)
                        }
                    }
                }
            }
        }

        val assetsFile = File(path, "src/main/assets")
        if (assetsFile.exists()) {
            var catalog = CatalogGenerate.generate(
                assetsFile.absolutePath, "assets",
                HashMap(), ArrayList(), Int.MAX_VALUE
            )
            if (StringUtils.isNotEmpty(catalog)) {
                catalog = catalog.replace("\\| null".toRegex(), "")
                builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append("# main/assets 目录结构")
                    .append(DevFinal.SYMBOL.NEW_LINE_X2)
                    .append(catalog)
            }
        }

        val libsFile = File(path, "/libs")
        if (libsFile.exists()) {
            val libsPath = libsFile.absolutePath
            val lists = FileUtils.listFilesInDir(libsFile, true)
            val listNames: MutableList<String> = ArrayList()
            val libsBuilder = StringBuilder()
            for (file in lists) {
                var filePath = file.absolutePath
                val tempPath = File(libsFile, file.name).absolutePath
                if (filePath == tempPath) {
                    if (file.isFile) {
                        filePath = null
                        listNames.add(file.name)
                    } else {
                        filePath = String.format("【%s】", file.name)
                    }
                } else {
                    filePath = filePath.replace(libsPath, "")
                }
                if (filePath != null) {
                    libsBuilder.append(filePath).append(DevFinal.SYMBOL.NEW_LINE_X2)
                }
            }
            if (listNames.size != 0) {
                libsBuilder.append("【libs - root】").append(DevFinal.SYMBOL.NEW_LINE_X2)
                for (name in listNames) {
                    libsBuilder.append(name).append(DevFinal.SYMBOL.NEW_LINE_X2)
                }
            }
            builder.append(DevFinal.SYMBOL.NEW_LINE)
                .append("# main/libs 目录结构")
                .append(DevFinal.SYMBOL.NEW_LINE_X2)
                .append(libsBuilder)
        }
    }

    // ==========
    // = 插入文档 =
    // ==========

    /**
     * 插入文档头部内容
     * @param builder 待插入 [StringBuilder]
     * @param path    文件路径 ( 模块 )
     */
    fun insertHeadREADME(
        builder: StringBuilder,
        path: String
    ) {
        if (StringUtils.equals(path, Config.INTERESTING_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录主要存储一些有趣的试验、代码生成、规范检测项目")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        } else if (StringUtils.equals(path, Config.COMPONENT_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录存储组件化各组件相关 ( core、library ) 等代码，方便统一查找、维护")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        } else if (StringUtils.equals(path, Config.CORE_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录属于核心基础库代码，整个组件化项目基于该基础上进行开发")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        } else if (StringUtils.equals(path, Config.LIBS_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录属于 项目模块快捷工具封装复用、第三方库 clone 对源码进行差异化修改使用等存储目录")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        } else if (StringUtils.equals(path, Config.APP_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录存储可运行的 Module 应用、主体应用程序")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        } else if (StringUtils.equals(path, Config.MODULE_LOCAL_PATH)) {
            builder.append("# About")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("该目录下的 Module 在 `isModular=true` 的情况下，都属于独立的应用可单独运行")
            builder.append("，为 `false` 则都属于功能模块，被主体应用 ( 壳 ) 所依赖使用")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
        }
    }

    /**
     * 插入文档尾部内容
     * @param builder 待插入 [StringBuilder]
     * @param path    文件路径 ( 模块 )
     */
    fun insertTailREADME(
        builder: StringBuilder,
        path: String
    ) {
        if (StringUtils.equals(path, Config.CORE_LOCAL_PATH)) {
            builder.append(DevFinal.SYMBOL.NEW_LINE)
            builder.append("# core/core")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("> 该 Module 依赖 core 核心开发库、核心第三方库等")
            builder.append(DevFinal.SYMBOL.NEW_LINE).append(">").append(DevFinal.SYMBOL.NEW_LINE)
            builder.append("> 统一维护核心库依赖，对外只需要依赖该 Module 便可使用整个核心模块 ( core 文件以及内部所有 libs )")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("## core/core_base_lib")
            builder.append(DevFinal.SYMBOL.NEW_LINE_X2)
            builder.append("> 该 Module 基于 Dev 系列开发库搭建，且不存在任何代码属于核心 lib 依赖 ( 全部开发基于该 module )")
            builder.append(DevFinal.SYMBOL.NEW_LINE).append(">").append(DevFinal.SYMBOL.NEW_LINE)
            builder.append("> 用于统一维护基础核心开发库依赖，如有必须依赖底层库在此添加")
        }
    }

    /**
     * 插入文档尾部内容 ( 各个 lib、module )
     * @param builder 待插入 [StringBuilder]
     * @param path    文件路径 ( 模块 )
     */
    fun insertModuleTailREADME(
        builder: StringBuilder,
        path: String
    ) {
        // 属于 lib_circle_igview lib
        if (path.indexOf("lib_circle_igview") != -1) {
            builder.append(DevFinal.SYMBOL.NEW_LINE)
                .append("# Usage").append(DevFinal.SYMBOL.NEW_LINE_X2)
                .append("```xml").append(DevFinal.SYMBOL.NEW_LINE)
                .append("<de.hdodenhof.circleimageview.CircleImageView")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\txmlns:app=\"http://schemas.android.com/apk/res-auto\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tandroid:id=\"@+id/vid_profile_civ\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tandroid:layout_width=\"96dp\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tandroid:layout_height=\"96dp\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tandroid:src=\"@drawable/profile\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tapp:civ_border_width=\"2dp\"")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("\tapp:civ_border_color=\"#FF000000\"/>")
                .append(DevFinal.SYMBOL.NEW_LINE)
                .append("```")
        }
    }
}