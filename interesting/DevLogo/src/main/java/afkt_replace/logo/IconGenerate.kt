package afkt_replace.logo

import dev.utils.common.FileUtils
import sun.font.FontDesignMetrics
import java.awt.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * detail: Icon 生成
 * @author Ttt
 */
internal object IconGenerate {

    private val TYPE = "png"

    // ==============
    // = 对外公开方法 =
    // ==============

    /**
     * 生成 ICON 入口
     * @param iconList 待生成 ICON 列表
     */
    fun generate(iconList: List<IconLauncher.ICON>) {
        iconList.forEach {
            Thread.sleep(150)
            generateImage(it)
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    private fun generateImage(icon: IconLauncher.ICON) {
        FileUtils.createFolder(icon.folderPath)
        // 获取系统全部字体并使用第一个
        val environment = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val fonts = environment.allFonts
        val font = fonts[0]

        // ==========
        // = 圆角图片 =
        // ==========

        val image = createImage(icon, font, false)
        ImageIO.write(image, TYPE, icon.getIconFile())

        // ==========
        // = 圆形图片 =
        // ==========

        val roundImage = createImage(icon, font, true)
        ImageIO.write(roundImage, TYPE, icon.getRoundIconFile())
    }

    /**
     * 创建图片
     * @param icon ICON
     * @param font 绘制字体
     * @param isRound 是否圆角图片
     * @return BufferedImage
     */
    private fun createImage(
        icon: IconLauncher.ICON,
        font: Font,
        isRound: Boolean
    ): BufferedImage {
        val width = icon.size.width
        val height = icon.size.height
        // 创建图片
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        // 创建画笔
        val g2d: Graphics2D = image.createGraphics()

        // 设置对线段的锯齿状边缘处理
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        )
        // 设置画笔颜色
        g2d.color = Color.WHITE
        // 绘制底图
        if (isRound) {
            g2d.fillRoundRect(0, 0, width, height, width, height)
        } else {
            // 圆角比例, 以 192 圆角 50px 为例
            val radiusScale = (192.0F - 50.0F) / 192.0F
            val radius = (width * (1.0F - radiusScale)).toInt()

            g2d.fillRoundRect(0, 0, width, height, radius, radius)
        }

        // 左右边距比例, 以 192 居左 32px 为例
        val marginScale = (192.0F - 32.0F) / 192.0F
        val margin = (width * (1.0F - marginScale)).toInt()
        // 实际可绘制宽度
        val drawWidth = width - (margin * 2)
        // 获取文字大小
        val fontSize = calculateFontSize(font, icon.text, 200, drawWidth)
        // 创建对应大小的字体
        val newFont = Font(font.name, font.style, fontSize)
        // 获取文本宽高
        val fontMetrics = FontDesignMetrics.getMetrics(newFont)
//        val textWidth = calculateTextWidth(newFont, icon.text)
//        val textHeight = fontMetrics.height
        val fontAscent = fontMetrics.ascent
        // 计算 XY 位置
        val drawX = (margin)
        val drawY = ((height + fontAscent) * 0.5F).toInt()

        // 设置字体
        g2d.font = newFont
        // 设置画笔颜色
        g2d.color = Color.BLACK
        // 绘制文案在中间
        g2d.drawString(icon.text, drawX, drawY)
        // 释放资源
        g2d.dispose()
        return image
    }

    /**
     * 计算字体大小
     * @param font Font
     * @param text 待计算文本
     * @param size 字体大小
     * @param drawWidth 可绘制宽度
     * @return 文本字体
     */
    private fun calculateFontSize(
        font: Font,
        text: String,
        size: Int,
        drawWidth: Int
    ): Int {
        val newFont = Font(font.name, font.style, size)
        return if (calculateTextWidth(newFont, text) <= drawWidth) {
            size
        } else {
            calculateFontSize(newFont, text, size - 1, drawWidth)
        }
    }

    /**
     * 计算文本宽度
     * @param font Font
     * @param text 待计算文本
     * @return 文本宽度
     */
    private fun calculateTextWidth(
        font: Font,
        text: String
    ): Int {
        val metrics = FontDesignMetrics.getMetrics(font)
        var width = 0
        for (element in text) {
            width += metrics.charWidth(element)
        }
        return width
    }
}