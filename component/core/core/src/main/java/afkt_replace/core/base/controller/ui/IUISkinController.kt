package afkt_replace.core.base.controller.ui

import afkt_replace.core.ui.skin.AppThemeKey

/**
 * detail: UI 换肤基类控制器接口
 * @author Ttt
 */
interface IUISkinController {

    // ===========
    // = UI Skin =
    // ===========

    /**
     * 是否全局同步换肤
     * @return `true` yes, `false` no
     */
    fun isSyncSkin(): Boolean = true

    /**
     * 忽略不进行全局换肤资源
     * @return [AppThemeKey]
     */
    fun ignoreSkinList(): List<String> = arrayListOf()
}