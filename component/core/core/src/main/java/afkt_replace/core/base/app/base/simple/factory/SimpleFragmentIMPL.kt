package afkt_replace.core.base.app.base.simple.factory

import afkt_replace.core.lib.base.controller.ui.theme.FragmentUITheme

class SimpleFragmentIMPL<SimpleTClass> : BaseSimpleAgile<SimpleTClass, FragmentUITheme> {

    private constructor(
        simple_Init: ((SimpleTClass) -> Unit)?,
        simple_Start: ((SimpleTClass) -> Unit)?,
        simple_PreLoad: ((SimpleTClass) -> Unit)?,
        simple_Agile: ((SimpleTClass) -> Unit)?,
        simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)?
    ) : super(simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme)

    companion object {

        fun <SimpleTClass> of(
            simple_Init: ((SimpleTClass) -> Unit)? = null,
            simple_Start: ((SimpleTClass) -> Unit)? = null,
            simple_PreLoad: ((SimpleTClass) -> Unit)? = null,
            simple_Agile: ((SimpleTClass) -> Unit)? = null,
            simple_UITheme: ((FragmentUITheme) -> FragmentUITheme)? = null
        ): SimpleFragmentIMPL<SimpleTClass> {
            return SimpleFragmentIMPL(
                simple_Init, simple_Start, simple_PreLoad, simple_Agile, simple_UITheme
            )
        }
    }
}