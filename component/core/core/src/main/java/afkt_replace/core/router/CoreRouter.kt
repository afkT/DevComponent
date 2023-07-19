package afkt_replace.core.router

import afkt_replace.core.router.module.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: Core Route Path
 * @author Ttt
 * 跳转使用 ARouter.getInstance().build(path, group)
 * 为了明确表达出跳转的属于什么功能模块, 需要传入 group
 * 不使用 ARouter.getInstance().build(path) 跳转
 */
object CoreRouter {

    const val GROUP = "CORE"

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 [GROUP] 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 [GROUP] ( Module )
     */
    fun build(path: String): Postcard {
        return AppRouter.buildByUri(path)
    }
}