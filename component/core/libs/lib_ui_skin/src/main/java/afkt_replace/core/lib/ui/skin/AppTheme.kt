package afkt_replace.core.lib.ui.skin

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.mvvm.base.attribute.Margins

/**
 * detail: APP 主题样式
 * @author Ttt
 * 整个 APP 主题、换肤相关控制
 */
object AppTheme {

    /**
     * 获取当前全局主题样式
     * @return AppThemeRes
     * 如果需要修改某个全局样式可以通过该方法获取全局样式, 并重新调用 AppThemeSkin 进行切换
     * val current = AppTheme.currentAppTheme()
     * current.themeBackground.value = ColorDrawable(Color.BLACK)
     * AppThemeSkin.skin(current)
     */
    fun currentAppTheme(): AppThemeRes {
        return AppThemeRes(null, false)
    }

    // ==========
    // = 对外公开 =
    // ==========

    /**
     * 主题背景色
     */
    internal val _themeBackground = MutableLiveData<Drawable>()
    val themeBackground: LiveData<Drawable>
        get() = _themeBackground

    // =========
    // = 状态栏 =
    // =========

    /**
     * 状态栏高度
     */
    internal val _statusBarHeight = MutableLiveData<Int>()
    val statusBarHeight: LiveData<Int>
        get() = _statusBarHeight

    /**
     * 状态栏背景色
     */
    internal val _statusBarBackground = MutableLiveData<Drawable>()
    val statusBarBackground: LiveData<Drawable>
        get() = _statusBarBackground

    // =========
    // = 标题栏 =
    // =========

    /**
     * 标题栏高度
     */
    internal val _titleBarHeight = MutableLiveData<Int>()
    val titleBarHeight: LiveData<Int>
        get() = _titleBarHeight

    /**
     * 标题栏背景色
     */
    internal val _titleBarBackground = MutableLiveData<Drawable>()
    val titleBarBackground: LiveData<Drawable>
        get() = _titleBarBackground

    // =======
    // = 标题 =
    // =======

    /**
     * 标题字体颜色
     */
    internal val _titleTextColor = MutableLiveData<ColorStateList>()
    val titleTextColor: LiveData<ColorStateList>
        get() = _titleTextColor

    /**
     * 标题字体大小
     */
    internal val _titleTextSize = MutableLiveData<Int>()
    val titleTextSize: LiveData<Int>
        get() = _titleTextSize

    // =================
    // = 标题栏 - 返回键 =
    // =================

    /**
     * 标题返回键图标
     */
    internal val _titleBackIcon = MutableLiveData<Drawable>()
    val titleBackIcon: LiveData<Drawable>
        get() = _titleBackIcon

    /**
     * 标题返回键图标边距
     */
    internal val _titleBackMargin = MutableLiveData<Margins>()
    val titleBackMargin: LiveData<Margins>
        get() = _titleBackMargin
}