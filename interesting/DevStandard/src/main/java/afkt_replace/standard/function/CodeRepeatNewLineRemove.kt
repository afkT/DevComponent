package afkt_replace.standard.function

import afkt_replace.standard.Utils
import afkt_replace.standard.catalog.Config
import dev.utils.common.FileIOUtils
import dev.utils.common.FileUtils
import dev.utils.common.MapUtils
import dev.utils.common.StringUtils
import java.io.File
import java.io.FileFilter

/**
 * detail: 代码重复换行检测
 * @author Ttt
 */
object CodeRepeatNewLineRemove {

    // 代码注释重复换行记录
    private val sAnnotationRepeatLineMap = HashMap<String, List<String>>()

    // 匹配文件后缀
    private val SUFFIX = arrayOf("kt", "xml", "java", "gradle")

    @JvmStatic
    fun main(args: Array<String>) {
        // 获取文件列表
        val lists = FileUtils.listFilesInDirWithFilter(
            Config.PROJECT_PATH, FileFilter { file ->
                val absolutePath = file.absolutePath
                if (Code.isHidden(absolutePath)) return@FileFilter false
                if (Code.isBuild(absolutePath)) return@FileFilter false
                if (file.isDirectory) return@FileFilter false

                val fileSuffix = FileUtils.getFileSuffix(file)
                return@FileFilter StringUtils.isOrEquals(fileSuffix, *SUFFIX)
            }, true
        )
        for (file in lists) {
            readFile(file)
        }
        val json = Utils.toJsonFormat(sAnnotationRepeatLineMap, true)
        // 打印文件多余换行信息
        println(json)
    }

    /**
     * 读取文件
     * @param file 文件
     */
    private fun readFile(file: File) {
        // 读取文件内容
        val lists = FileIOUtils.readFileToList(file, 0, Int.MAX_VALUE)

        // 判断是否需要判断 重复出现情况
        var repeat = false
        // 循环判断
        for (i in 0 until lists.size) {
            // 获取每一行代码
            val code = lists[i]
            // 判断是否 null
            val isSpace = StringUtils.isSpace(code)
            // 防止为 null
            if (!isSpace) {
                repeat = false // 不需要判断重复
            } else {
                if (code != null && repeat) {
                    MapUtils.putToList(
                        sAnnotationRepeatLineMap,
                        file.name,
                        (i + 1).toString()
                    )
                }
                // 表示需要检测重复
                repeat = true
            }
        }
    }
}