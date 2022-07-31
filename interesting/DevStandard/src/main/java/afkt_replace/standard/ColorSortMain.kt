package afkt_replace.standard

import afkt_replace.standard.catalog.Config
import dev.utils.DevFinal
import dev.utils.common.CollectionUtils
import dev.utils.common.ColorUtils
import dev.utils.common.ColorUtils.ColorInfo
import dev.utils.common.FileUtils
import dev.utils.common.StringUtils
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import javax.xml.parsers.SAXParserFactory

/**
 * detail: Color 排序方法
 * @author Ttt
 * 可用于 colors.xml 颜色排序, 并且统一整个项目 color 规范替换命名等等
 */
object ColorSortMain {

    // colors.xml 文件路径
    private const val COLORS_XML = "/src/main/res/values/colors.xml"

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        // colors.xml 文件地址
        val xmlPath = File(
            "${Config.PROJECT_PATH}/app", COLORS_XML
        ).absolutePath

        // color xml 排序
        colorXMLSort(xmlPath)
    }

    /**
     * Color Xml 颜色排序方法
     * @param xmlPath colors.xml 文件路径
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun colorXMLSort(xmlPath: String) {
        // 解析 colors.xml
        SAXXml(xmlPath).analysisColorsXml(object : SAXXml.DocumentListener {
            override fun onEnd(lists: List<ColorInfo>) {
                if (CollectionUtils.isEmpty(lists)) {
                    println(" list is empty")
                    return
                }
                // 饱和度排序
                ColorUtils.sortSaturation(lists)
                // 色相排序
                ColorUtils.sortHUE(lists)
                // 生成 XML 文件内容
                val content = Builder.createXML(lists)
                // 覆盖处理
                val result = FileUtils.saveFile(xmlPath, StringUtils.getBytes(content))
                // 获取结果
                println("result: $result")
            }
        })
    }

    /**
     * detail: XML 创建处理
     * @author Ttt
     */
    internal object Builder {

        // XML 内容
        private const val XML_CONTENT = "%s\t<color name=\"%s\">%s</color>"

        /**
         * 创建 XML
         * @param lists Color 信息集合
         * @return XML 文件内容
         */
        fun createXML(lists: List<ColorInfo>): String {
            val builder = StringBuilder()
            builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
            builder.append(DevFinal.SYMBOL.NEW_LINE)
            builder.append("<resources>")
            // 解析数据
            for (colorInfo in lists) {
                builder.append(
                    String.format(
                        XML_CONTENT, DevFinal.SYMBOL.NEW_LINE,
                        colorInfo.key, colorInfo.value
                    )
                )
            }
            builder.append(DevFinal.SYMBOL.NEW_LINE)
            builder.append("</resources>")
            return builder.toString()
        }
    }

    /**
     * detail: SAX 解析 XML 内部类
     * @author Ttt
     */
    internal class SAXXml(
        // colors.xml 文件地址
        private val xmlPath: String
    ) {

        /**
         * 解析 Colors.xml 内容
         * @param listener 监听事件
         * @throws Exception
         */
        @Throws(Exception::class)
        fun analysisColorsXml(listener: DocumentListener) {
            // 获取 SAXParserFactory 实例
            val factory = SAXParserFactory.newInstance()
            // 获取 SAXParser 实例
            val saxParser = factory.newSAXParser()
            // 创建 Handler 对象并进行解析
            saxParser.parse(xmlPath, SAXHandler(listener))
        }

        /**
         * detail: 解析 Handler
         * @author Ttt
         */
        internal inner class SAXHandler(
            // 解析事件
            private val listener: DocumentListener
        ) : DefaultHandler() {

            private var colorKey: String? = null
            private var colorValue: String? = null

            // 解析集合
            private val lists: MutableList<ColorInfo> = ArrayList()

            @Throws(SAXException::class)
            override fun startDocument() {
                super.startDocument() // SAX 解析开始
            }

            @Throws(SAXException::class)
            override fun endDocument() {
                super.endDocument() // SAX 解析结束
                // 触发回调
                listener.onEnd(lists)
            }

            @Throws(SAXException::class)
            override fun startElement(
                uri: String,
                localName: String,
                qName: String,
                attributes: Attributes
            ) {
                super.startElement(uri, localName, qName, attributes)
                if (qName == "color") {
                    colorKey = attributes.getValue("name")
                }
            }

            @Throws(SAXException::class)
            override fun endElement(
                uri: String,
                localName: String,
                qName: String
            ) {
                super.endElement(uri, localName, qName)
                if (qName == "color") {
                    lists.add(ColorInfo(colorKey, colorValue))
                }
            }

            @Throws(SAXException::class)
            override fun characters(
                ch: CharArray,
                start: Int,
                length: Int
            ) {
                super.characters(ch, start, length)
                val value = String(ch, start, length).trim { it <= ' ' }
                if (value != "") {
                    colorValue = value // 可设置全部值转大写、小写
                }
            }
        }

        /**
         * detail: Xml Document 解析监听事件
         * @author Ttt
         */
        interface DocumentListener {
            /**
             * 解析结束触发
             * @param lists 解析集合
             */
            fun onEnd(lists: List<ColorInfo>)
        }
    }
}