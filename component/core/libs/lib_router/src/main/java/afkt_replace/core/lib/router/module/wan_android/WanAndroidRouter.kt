package afkt_replace.core.lib.router.module.wan_android

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

/**
 * detail: WanAndroid Module Router
 * @author Ttt
 */
object WanAndroidRouter {

    const val GROUP = "wan_android"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/wan_android/main"

    // WanAndroid 文章 Fragment
    const val PATH_ARTICLE_FRAGMENT = "/wan_android/article/fragment"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Postcard {
        return ARouter.getInstance().build(path, GROUP)
    }
}