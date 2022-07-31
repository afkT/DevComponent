package afkt_replace.standard.function

import afkt_replace.standard.catalog.Config
import dev.utils.DevFinal
import dev.utils.common.FileUtils
import dev.utils.common.StringUtils
import dev.utils.common.assist.search.FileDepthFirstSearchUtils
import java.io.File
import java.io.UnsupportedEncodingException

/**
 * detail: Xml Encoding 追加处理
 * @author Ttt
 */
object CodeXmlEncoding {

    private val sSets = LinkedHashSet<String>()

    // 开头
    private const val STARTS_WITH = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"

    // 追加内容
    private val APPEND = STARTS_WITH + DevFinal.SYMBOL.NEW_LINE

    // 文件后缀
    private val SUFFIX = arrayOf("xml")

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
                    if (!StringUtils.isOrEquals(fileSuffix, *SUFFIX)) {
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
                        if (!it.startsWith(STARTS_WITH)) {
                            // 删减内容
                            val appendData = APPEND + it
                            try {
                                // 替换内容
                                FileUtils.saveFile(
                                    file.absolutePath,
                                    appendData.toByteArray(charset(DevFinal.ENCODE.UTF_8))
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