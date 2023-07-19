package afkt_replace.module.template

import afkt_replace.core.lib.router.module.template.TemplateNav

/**
 * detail: TemplateNav 跳转参数构建
 * @author Ttt
 */
object TemplateNavBuild {

    /**
     * 跳转 End Page Path
     */
    fun routerEndPage() {
        TemplateNav.buildEndPage(
            "End Page"
        ).navigation()
    }
}