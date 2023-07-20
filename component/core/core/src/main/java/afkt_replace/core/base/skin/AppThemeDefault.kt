package afkt_replace.core.base.skin

import afkt_replace.core.R
import afkt_replace.core.ui.skin.AppThemeRes
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import dev.mvvm.base.attribute.Margins
import dev.mvvm.utils.size.AppSize
import dev.utils.app.ResourceUtils
import dev.utils.app.ScreenUtils

/**
 * detail: App 默认主题样式值
 * @author Ttt
 */
object AppThemeDefault {

    // 主题背景色
    val THEME_BACKGROUND = ColorDrawable(ResourceUtils.getColor(R.color.colorPrimary))

    // =========
    // = 状态栏 =
    // =========

    // 状态栏高度
    val STATUS_BAR_HEIGHT = ScreenUtils.getStatusBarHeight()

    // 状态栏背景色
    val STATUS_BAR_BACKGROUND = ColorDrawable(ResourceUtils.getColor(R.color.colorPrimary))

    // =========
    // = 标题栏 =
    // =========

    // 标题栏高度
    val TITLE_BAR_HEIGHT = AppSize.dp2px(56.0F)

    // 标题栏背景色
    val TITLE_BAR_BACKGROUND = ColorDrawable(ResourceUtils.getColor(R.color.colorPrimary))

    // =======
    // = 标题 =
    // =======

    // 标题字体颜色
    val TITLE_TEXT_COLOR = ColorStateList.valueOf(Color.WHITE)

    // 标题字体大小
    val TITLE_TEXT_SIZE = AppSize.sp2px(18.0F)

    // =================
    // = 标题栏 - 返回键 =
    // =================

    // 标题返回键图标
    val TITLE_BACK_ICON = ResourceUtils.getDrawable(R.drawable.op_arrow_left_white)

    // 标题返回键图标边距
    val TITLE_BACK_MARGIN = Margins(AppSize.dp2px(10.0F))

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 全局 APP 主题样式资源类创建
     * @return AppThemeRes
     */
    internal fun globalAppThemeRes(): AppThemeRes {
        return AppThemeRes(null, false).apply {

            // 主题背景色
            themeBackground.value = THEME_BACKGROUND

            // 状态栏高度
            statusBarHeight.value = STATUS_BAR_HEIGHT
            // 状态栏背景色
            statusBarBackground.value = STATUS_BAR_BACKGROUND

            // 标题栏高度
            titleBarHeight.value = TITLE_BAR_HEIGHT
            // 标题栏背景色
            titleBarBackground.value = TITLE_BAR_BACKGROUND

            // 标题字体颜色
            titleTextColor.value = TITLE_TEXT_COLOR
            // 标题字体大小
            titleTextSize.value = TITLE_TEXT_SIZE

            // 标题返回键图标
            titleBackIcon.value = TITLE_BACK_ICON
            // 标题返回键图标边距
            titleBackMargin.value = TITLE_BACK_MARGIN
        }
    }
}