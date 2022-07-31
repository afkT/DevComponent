package afkt_replace.replace

import dev.utils.DevFinal
import dev.utils.common.FileUtils
import dev.utils.common.StringUtils
import dev.utils.common.assist.search.FileDepthFirstSearchUtils
import java.io.File

object Code {

    // 当前目录
    val USER_DIR = System.getProperty("user.dir")

    // 项目路径
    val PROJECT_PATH = File(USER_DIR).absolutePath

    // 组件化项目包名 ( 目前, 待替换 )
    const val COMPONENT_PACKNAME = "afkt_replace"

    // ==========
    // = 替换参数 =
    // ==========

    // 替换包名
    const val REPLACE_PACKNAME = ""

    // =============
    // = 对外公开方法 =
    // =============

    /**
     * 是否隐藏文件、文件夹判断
     * @param path 待判断文件路径
     * @return `true` yes, `false` no
     */
    fun isHidden(path: String?): Boolean {
        return path?.let {
            val temp: String = it.replace("/../..".toRegex(), "")
                .replace("\\...\\...".toRegex(), "")
                .replace("\\..\\..".toRegex(), "")
            // 判断是否存在特殊情况
            if (temp.contains("..")) {
                println("path replace 未彻底 : $it")
            }
            return temp.contains("\\.") || temp.contains("/.")
        } ?: false
    }

    /**
     * 是否 Build 文件、文件夹
     * @param path 待判断文件路径
     * @return `true` yes, `false` no
     */
    fun isBuild(path: String?): Boolean {
        return path?.let {
            if (it.contains("\\build\\")) return true
            if (it.contains("/build/")) return true
            return false
        } ?: false
    }

    /**
     * 转换 UTF-8 编码
     * @return String
     */
    fun String.toUTF8(): String {
        return String(this.toByteArray(charset(DevFinal.ENCODE.UTF_8)))
    }

    // =

    /**
     * 转换搜索结果数据, 返回待替换文件列表
     * @param lists 根目录的子文件目录集合
     * @return 待替换文件列表
     */
    fun toReplaceDirectoryList(lists: List<FileDepthFirstSearchUtils.FileItem>): List<File> {
//        printOriginal(0, lists)

        val fileList = mutableListOf<File>()
        queryDirectory(fileList, lists)
        printReplace(fileList)
        return fileList
    }

    /**
     * 替换组件化模板包名
     * @param fileList 搜索结果
     * <p></p>
     * 例: 项目包名为 com.afkt.shop
     * 修改以下参数的结果为
     * 把项目包名修改为 com.afkt.shop
     *
     * // 替换包名
     * const val REPLACE_PACKNAME = "com.afkt.shop"
     *
     * 执行成功后续操作:
     * 全局搜索 afkt_replace ( COMPONENT_PACKNAME ) 字符串
     * 并替换为 com.afkt.shop ( REPLACE_PACKNAME )
     * 至此整个流程结束, 成功将该项目包名替换为指定包名
     * 最后只需 clean project 并进行 rebuild 即可使用该组件化模板进行个人、公司项目开发
     * tips:
     * 项目文件名 ( DevComponent ) 直接手动修改即可
     * 执行该操作结束后记得把 .git 文件夹删除, 再 push 到 git 服务器上
     */
    fun replaceComponent(fileList: List<File>) {
        if (!StringUtils.isSpace(REPLACE_PACKNAME)) {
            // 判断包名是否需要创建文件夹
            val directory = if (REPLACE_PACKNAME.contains("\\.".toRegex())) {
                // 包名替换为 xx/xx/xx path 结构
                REPLACE_PACKNAME.replace(
                    "\\.".toRegex(), "/"
                )
            } else {
                REPLACE_PACKNAME
            }
            // 循环目录
            fileList.forEach {
                if (it.exists()) {
                    val newFile = FileUtils.getFile(it.parent, directory)
                    FileUtils.createFolder(newFile)
                    // 移动文件夹 - 如果存在则进行覆盖
                    FileUtils.moveDir(it, newFile) { true }
                }
            }
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 搜索待替换文件夹
     * @param fileList 搜索结果
     * @param lists 根目录的子文件目录集合
     */
    private fun queryDirectory(
        fileList: MutableList<File>,
        lists: List<FileDepthFirstSearchUtils.FileItem>
    ) {
        lists.forEach continueTag@{
            val fileName = it.file.name
            // 属于待替换包名并且是文件夹才进行处理 ( 可不用判断是否文件夹, 搜索条件做了限制 )
            if (it.file.isDirectory && COMPONENT_PACKNAME == fileName) {
                fileList.add(it.file)
                return@continueTag
            }
            if (it.listChilds.size != 0) {
                queryDirectory(fileList, it.listChilds)
            }
        }
    }

    /**
     * 打印全部目录结构 ( 调试使用 )
     * @param line 目录行数
     * @param lists 根目录的子文件目录集合
     */
    private fun printOriginal(
        line: Int,
        lists: List<FileDepthFirstSearchUtils.FileItem>
    ) {
        lists.forEach {
            print(StringUtils.forString(line, DevFinal.SYMBOL.SPACE))
            print(" - ")
            println(it.file.absolutePath)
            if (it.listChilds.size != 0) {
                printOriginal(line + 1, it.listChilds)
            }
        }
    }

    /**
     * 输出待替换文件目录
     * @param fileList 筛选后的目录
     */
    private fun printReplace(fileList: MutableList<File>) {
        println("待替换文件目录:".toUTF8())
        fileList.forEach {
            println(it.absolutePath)
        }
    }
}