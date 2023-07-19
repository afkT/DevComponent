package afkt_replace.core.lib.ui.widget.extension

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import dev.utils.app.RecyclerViewUtils
import dev.widget.decoration.linear.FirstLinearColorItemDecoration
import dev.widget.decoration.linear.LastLinearColorItemDecoration
import dev.widget.decoration.linear.LinearColorItemDecoration

// ===================================
// = Dev 系列库 ItemDecoration 方法封装 =
// ===================================

/**
 * 移除 RecyclerView 全部 ItemDecoration
 * @return RecyclerView
 */
fun RecyclerView.removeAllItemDecoration(): RecyclerView {
    RecyclerViewUtils.removeAllItemDecoration(this)
    return this
}

/**
 * 添加 LinearLayoutManager 分割线处理 ( 第一条数据 )
 * @param height 分割线高度 ( Horizontal 为宽度 )
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 * 第一条数据顶部添加一条分割线
 */
fun RecyclerView.addFirstLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    RecyclerViewUtils.addItemDecoration(
        this, FirstLinearColorItemDecoration(vertical, height, color)
    )
    return this
}

/**
 * 添加 LinearLayoutManager 分割线处理 ( 最后一条数据 )
 * @param height 分割线高度 ( Horizontal 为宽度 )
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 * 最后一条数据底部添加一条分割线
 */
fun RecyclerView.addLastLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    RecyclerViewUtils.addItemDecoration(
        this, LastLinearColorItemDecoration(vertical, height, color)
    )
    return this
}

/**
 * 添加 LinearLayoutManager 分割线处理 ( 每一条数据 )
 * @param height 分割线高度 ( Horizontal 为宽度 )
 * @param vertical 分割线绘制方向 ( RecyclerView Orientation )
 * @param color 分割线画笔颜色
 * @return RecyclerView
 * 每一条数据底部添加一条分割线, 最后一条数据不绘制 ( 绘制 ItemCount - 1 条分割线 )
 */
fun RecyclerView.addLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    RecyclerViewUtils.addItemDecoration(
        this, LinearColorItemDecoration(vertical, height, color)
    )
    return this
}

// ==============
// = 简化快捷方法 =
// ==============

/**
 * 清空全部 ItemDecoration 并添加 LinearLayoutManager 分割线处理 ( 第一条数据 )
 * @return RecyclerView
 */
fun RecyclerView.onlyFirstLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addFirstLinearColorItemDecoration(
        height, vertical, color
    )
}

/**
 * 清空全部 ItemDecoration 并添加 LinearLayoutManager 分割线处理 ( 最后一条数据 )
 * @return RecyclerView
 */
fun RecyclerView.onlyLastLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addLastLinearColorItemDecoration(
        height, vertical, color
    )
}

/**
 * 清空全部 ItemDecoration 并添加 LinearLayoutManager 分割线处理 ( 每一条数据 )
 * @return RecyclerView
 */
fun RecyclerView.onlyLinearColorItemDecoration(
    height: Float,
    vertical: Boolean = true,
    color: Int = Color.TRANSPARENT
): RecyclerView {
    return removeAllItemDecoration().addLinearColorItemDecoration(
        height, vertical, color
    )
}