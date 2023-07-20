package afkt_replace.core.ui.skin

/**
 * detail: AppTheme 相关方法合并
 * @author Ttt
 * 合并针对 AppTheme 初始化代码, 减少类繁琐代码方便阅读理解
 * 也方便统一排查、赋值
 */
internal object InitMerge {

    fun AppThemeSkin.mergeInitByAppThemeRes(appRes: AppThemeRes) {

        // 主题背景色
        commonSetValue(AppTheme._themeBackground, appRes.themeBackground)

        // =========
        // = 状态栏 =
        // =========

        // 状态栏高度
        commonSetValue(AppTheme._statusBarHeight, appRes.statusBarHeight)
        // 状态栏背景色
        commonSetValue(AppTheme._statusBarBackground, appRes.statusBarBackground)

        // =========
        // = 标题栏 =
        // =========

        // 标题栏高度
        commonSetValue(AppTheme._titleBarHeight, appRes.titleBarHeight)
        // 标题栏背景色
        commonSetValue(AppTheme._titleBarBackground, appRes.titleBarBackground)

        // =======
        // = 标题 =
        // =======

        // 标题字体颜色
        commonSetValue(AppTheme._titleTextColor, appRes.titleTextColor)
        // 标题字体大小
        commonSetValue(AppTheme._titleTextSize, appRes.titleTextSize)

        // =================
        // = 标题栏 - 返回键 =
        // =================

        // 标题返回键图标
        commonSetValue(AppTheme._titleBackIcon, appRes.titleBackIcon)
        // 标题返回键图标边距
        commonSetValue(AppTheme._titleBackMargin, appRes.titleBackMargin)
    }

    fun AppThemeRes.mergeInitAppThemeRes() {

        // 主题背景色
        commonObserve(
            AppThemeKey.themeBackground,
            AppTheme.themeBackground,
            this.themeBackground
        )

        // =========
        // = 状态栏 =
        // =========

        // 状态栏高度
        commonObserve(
            AppThemeKey.statusBarHeight,
            AppTheme.statusBarHeight,
            this.statusBarHeight
        )

        // 状态栏背景色
        commonObserve(
            AppThemeKey.statusBarBackground,
            AppTheme.statusBarBackground,
            this.statusBarBackground
        )

        // =========
        // = 标题栏 =
        // =========

        // 标题栏高度
        commonObserve(
            AppThemeKey.titleBarHeight,
            AppTheme.titleBarHeight,
            this.titleBarHeight
        )

        // 标题栏背景色
        commonObserve(
            AppThemeKey.titleBarBackground,
            AppTheme.titleBarBackground,
            this.titleBarBackground
        )

        // =======
        // = 标题 =
        // =======

        // 标题字体颜色
        commonObserve(
            AppThemeKey.titleTextColor,
            AppTheme.titleTextColor,
            this.titleTextColor
        )

        // 标题字体大小
        commonObserve(
            AppThemeKey.titleTextSize,
            AppTheme.titleTextSize,
            this.titleTextSize
        )

        // =================
        // = 标题栏 - 返回键 =
        // =================

        // 标题返回键图标
        commonObserve(
            AppThemeKey.titleBackIcon,
            AppTheme.titleBackIcon,
            this.titleBackIcon
        )

        // 标题返回键图标边距
        commonObserve(
            AppThemeKey.titleBackMargin,
            AppTheme.titleBackMargin,
            this.titleBackMargin
        )
    }
}