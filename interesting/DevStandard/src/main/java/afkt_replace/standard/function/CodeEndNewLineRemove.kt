package afkt_replace.standard.function

import afkt_replace.standard.catalog.Config
import dev.utils.DevFinal
import dev.utils.common.FileUtils
import dev.utils.common.StringUtils
import dev.utils.common.assist.search.FileDepthFirstSearchUtils
import java.io.File
import java.io.UnsupportedEncodingException

/**
 * detail: 代码结尾换行移除
 * @author Ttt
 */
object CodeEndNewLineRemove {

    private val sSets = LinkedHashSet<String>()

    // 结尾符号
    private const val SYMBOL = "" // }

    // 检查 Key
    private val END_KEY = SYMBOL + DevFinal.SYMBOL.NEW_LINE

    // 追加内容
    private const val APPEND = SYMBOL

    // 忽略文件后缀
    private val IGNORE_SUFFIX = arrayOf("md", "txt", "bat")

    // 是否忽略后缀
    private const val IS_IGNORE_SUFFIX = false

    @JvmStatic
    fun main(args: Array<String>) {
        FileDepthFirstSearchUtils()
            .setSearchHandler(object : FileDepthFirstSearchUtils.SearchHandler {
                override fun isHandlerFile(file: File): Boolean {
                    return true
                }

                override fun isAddToList(file: File): Boolean {
                    val absolutePath = file.absolutePath
                    if (Code.isHidden(absolutePath)) return false
                    if (Code.isBuild(absolutePath)) return false

                    val fileSuffix = FileUtils.getFileSuffix(file)
                    if (!IS_IGNORE_SUFFIX && StringUtils.isOrEquals(fileSuffix, *IGNORE_SUFFIX)) {
                        return true
                    }

                    var data: String? = null
                    try {
                        data = String(
                            FileUtils.readFileBytes(file),
                            charset(DevFinal.ENCODE.UTF_8)
                        )
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }
                    data?.let {
                        if (it.endsWith(END_KEY)) {
                            // 删减内容
                            val subData = it.substring(0, it.length - END_KEY.length) + APPEND
                            try {
                                // 替换内容
                                FileUtils.saveFile(
                                    file.absolutePath,
                                    subData.toByteArray(charset(DevFinal.ENCODE.UTF_8))
                                )
                            } catch (e: UnsupportedEncodingException) {
                                e.printStackTrace()
                            }
                            // 存储路径
                            sSets.add(FileUtils.getAbsolutePath(file))
                        }
                    }
                    return true
                }

                override fun onEndListener(
                    lists: List<FileDepthFirstSearchUtils.FileItem>,
                    startTime: Long,
                    endTime: Long
                ) {
                    for (path in sSets) {
                        println(path)
                    }
                    try {
                        println(String("搜索结束".toByteArray(charset(DevFinal.ENCODE.UTF_8))))
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }
                }
            }).query(Config.PROJECT_PATH, true)
    }
}