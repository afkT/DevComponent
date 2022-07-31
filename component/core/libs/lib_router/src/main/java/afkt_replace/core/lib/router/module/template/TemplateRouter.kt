package afkt_replace.core.lib.router.module.template

import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter

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

    // 对外公开 Fragment
    const val PATH_TEMPLATE_FRAGMENT = "/template/fragment"

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