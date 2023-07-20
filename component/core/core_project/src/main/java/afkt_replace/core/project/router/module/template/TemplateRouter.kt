package afkt_replace.core.project.router.module.template

import afkt_replace.core.router.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: Template Module Router
 * @author Ttt
 */
object TemplateRouter {

    const val GROUP = "template"

    // ========
    // = PATH =
    // ========

    // 模块入口
    const val PATH_MAIN = "/template/main"

    // ================
    // = PATH - Start =
    // ================

    // Start Fragment
    const val PATH_START_FRAGMENT = "/$GROUP/start/fragment"

    // ==============
    // = PATH - End =
    // ==============

    // End Activity
    const val PATH_END_ACTIVITY = "/$GROUP/end/activity"

    // End Fragment
    const val PATH_END_FRAGMENT = "/$GROUP/end/fragment"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    internal fun build(path: String): Postcard {
        return AppRouter.buildByUri(path)
    }
}