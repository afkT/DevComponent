package afkt_replace.replace

import afkt_replace.replace.Code.toUTF8
import dev.utils.common.assist.search.FileDepthFirstSearchUtils
import java.io.File

/**
 * detail: 快捷替换组件化模板包名
 * @author Ttt
 * DevReplace lib 主要用于替换该组件化模板项目名、包名
 * 便于直接使用该模板进行组件化开发, 减少使用者包名替换、重新搭建组件化流程
 */
internal object ReplaceMain {

    @JvmStatic
    fun main(args: Array<String>) {
        FileDepthFirstSearchUtils()
            .setSearchHandler(object : FileDepthFirstSearchUtils.SearchHandler {
                override fun isHandlerFile(file: File): Boolean {
                    return file.isDirectory
                }

                override fun isAddToList(file: File): Boolean {
                    val absolutePath = file.absolutePath
                    if (Code.isHidden(absolutePath)) return false
                    if (Code.isBuild(absolutePath)) return false
                    return file.isDirectory
                }

                override fun onEndListener(
                    lists: List<FileDepthFirstSearchUtils.FileItem>,
                    startTime: Long,
                    endTime: Long
                ) {
                    println("搜索结束".toUTF8())
                    val fileList = Code.toReplaceDirectoryList(lists)
                    Code.replaceComponent(fileList)
                    println("执行成功".toUTF8())
                }
            }).query(Code.PROJECT_PATH, true)
    }
}