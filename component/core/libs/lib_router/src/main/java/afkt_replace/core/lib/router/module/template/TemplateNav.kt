package afkt_replace.core.lib.router.module.template

import afkt_replace.core.lib.config.ParamConst
import com.therouter.router.Navigator

/**
 * detail: 具体 navigation 类
 * @author Ttt
 * 不封装在对应的 Router 类中, 统一通过 Nav 类进行跳转、获取
 * 方便后续二次封装、统一编辑等处理
 */
object TemplateNav {

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 GROUP 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 GROUP ( Module )
     */
    fun build(path: String): Navigator {
        return TemplateRouter.build(path)
    }

    // ==========
    // = 跳转方法 =
    // ==========

    /**
     * 模块入口路由跳转
     */
    fun routerMain() {
        build(TemplateRouter.PATH_MAIN).navigation()
    }

    // =======
    // = End =
    // =======

    /**
     * 构建 End Page Path Router
     * @param title Title
     * @return Navigator
     */
    fun buildEndPage(
        title: String?
    ): Navigator {
        return build(TemplateRouter.PATH_END_ACTIVITY).apply {
            withString(ParamConst.TITLE, title)
        }
    }
}