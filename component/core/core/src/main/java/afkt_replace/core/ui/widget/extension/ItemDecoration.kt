package afkt_replace.core.ui.widget.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dev.utils.app.RecyclerViewUtils
import dev.utils.app.image.ImageUtils

// =========================
// = ItemDecoration 方法封装 =
// =========================

// ============
// = Drawable =
// ============

/**
 * 添加 RecyclerView 分割线处理
 * @param drawable 分割线
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecoration(
    drawable: Drawable?,
    vertical: Boolean = true
): RecyclerView {
    drawable?.let {
        val itemDecoration = DividerItemDecoration(
            this.context, if (vertical) {
                LinearLayout.VERTICAL
            } else {
                LinearLayout.HORIZONTAL
            }
        ).apply {
            setDrawable(drawable)
        }
        RecyclerViewUtils.addItemDecoration(
            this, itemDecoration
        )
    }
    return this
}

/**
 * 添加 RecyclerView 分割线处理
 * @param drawable 分割线
 * @param height 分割线高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationHeight(
    drawable: Drawable?,
    height: Int,
    vertical: Boolean = true
): RecyclerView {
    if (height > 0) {
        addDividerItemDecorationHeight(
            ImageUtils.drawableToBitmap(drawable), height, vertical
        )
    }
    return this
}

// ==========
// = Bitmap =
// ==========

/**
 * 添加 RecyclerView 分割线处理
 * @param bitmap 分割线
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecoration(
    bitmap: Bitmap?,
    vertical: Boolean = true
): RecyclerView {
    return addDividerItemDecoration(
        ImageUtils.bitmapToDrawable(bitmap), vertical
    )
}

/**
 * 添加 RecyclerView 分割线处理
 * @param bitmap 分割线
 * @param height 分割线高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationHeight(
    bitmap: Bitmap?,
    height: Int,
    vertical: Boolean = true
): RecyclerView {
    if (height > 0) {
        bitmap?.let {
            val newBitmap = Bitmap.createBitmap(
                it.width, height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(it)
            canvas.drawBitmap(it, 0f, 0f, null)
            canvas.save()
            canvas.restore()
            addDividerItemDecoration(newBitmap, vertical)
        }
    }
    return this
}

// =========
// = Color =
// =========

/**
 * 添加 RecyclerView 分割线处理
 * @param width 分割线宽度
 * @param height 分割线高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationColor(
    width: Int,
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    if (width > 0 && height > 0) {
        val newBitmap = Bitmap.createBitmap(
            width, height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(newBitmap)
        canvas.drawColor(color)
        canvas.save()
        canvas.restore()
        addDividerItemDecoration(newBitmap, vertical)
    }
    return this
}

/**
 * 添加 RecyclerView 分割线处理
 * @param widthHeight 分割线宽高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationColorSquare(
    widthHeight: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return addDividerItemDecorationColor(
        widthHeight, widthHeight, vertical, color
    )
}

/**
 * 添加 RecyclerView 分割线处理
 * @param height 分割线高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationColorHeight(
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return addDividerItemDecorationColor(
        1, height, vertical, color
    )
}

/**
 * 添加 RecyclerView 分割线处理
 * @param height 分割线高度
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 */
fun RecyclerView.addDividerItemDecorationColorHeightBySelfWidth(
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return addDividerItemDecorationColor(
        width, height, vertical, color
    )
}

// ==============
// = 简化快捷方法 =
// ==============

// ============
// = Drawable =
// ============

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecoration(
    drawable: Drawable?,
    vertical: Boolean = true
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecoration(
        drawable, vertical
    )
}

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationHeight(
    drawable: Drawable?,
    height: Int,
    vertical: Boolean = true
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationHeight(
        drawable, height, vertical
    )
}

// ==========
// = Bitmap =
// ==========

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecoration(
    bitmap: Bitmap?,
    vertical: Boolean = true
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecoration(
        bitmap, vertical
    )
}

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationHeight(
    bitmap: Bitmap?,
    height: Int,
    vertical: Boolean = true
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationHeight(
        bitmap, height, vertical
    )
}

// =========
// = Color =
// =========

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationColor(
    width: Int,
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationColor(
        width, height, vertical, color
    )
}

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationColorSquare(
    widthHeight: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationColorSquare(
        widthHeight, vertical, color
    )
}

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationColorHeight(
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationColorHeight(
        height, vertical, color
    )
}

/**
 * 清空全部 ItemDecoration 并添加 RecyclerView 分割线处理
 * @return RecyclerView
 */
fun RecyclerView.onlyDividerItemDecorationColorHeightBySelfWidth(
    height: Int,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addDividerItemDecorationColorHeightBySelfWidth(
        height, vertical, color
    )
}