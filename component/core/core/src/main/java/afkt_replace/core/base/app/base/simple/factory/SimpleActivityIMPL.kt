package afkt_replace.core.base.app.base.simple.factory

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.theme.ActivityUITheme

class SimpleActivityIMPL<SimpleTClass : afkt_replace.core.base.app.BaseAppActivity<*, *>> : BaseSimpleAgile<SimpleTClass, ActivityUITheme> {

    private constructor(
        simple_Init: ((SimpleTClass) -> Unit)?,
        simple_Start: ((SimpleTClass) -> Unit)?,
        simple_PreLoad: ((SimpleTClass) -> Unit)?,
        simple_Agile: ((SimpleTClass) -> Unit)?,
        simple_UITheme: ((ActivityUITheme) -> ActivityUITheme)?
    ) : super(simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme)

    companion object {

        fun <SimpleTClass : afkt_replace.core.base.app.BaseAppActivity<*, *>> of(
            simple_Init: ((SimpleTClass) -> Unit)? = null,
            simple_Start: ((SimpleTClass) -> Unit)? = null,
            simple_PreLoad: ((SimpleTClass) -> Unit)? = null,
            simple_Agile: ((SimpleTClass) -> Unit)? = null,
            simple_UITheme: ((ActivityUITheme) -> ActivityUITheme)? = null
        ): SimpleActivityIMPL<SimpleTClass> {
            return SimpleActivityIMPL(
                simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
            )
        }
    }
}