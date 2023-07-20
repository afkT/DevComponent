package afkt_replace.core.base.app.base.simple.factory

import afkt_replace.core.base.app.base.simple.SimpleAgileFactory

open class BaseSimpleAgile<SimpleTClass, SimpleTUITheme> : SimpleAgileFactory<SimpleTClass, SimpleTUITheme> {

    private val simple_Init: ((SimpleTClass) -> Unit)?
    private val simple_Start: ((SimpleTClass) -> Unit)?
    private val simple_PreLoad: ((SimpleTClass) -> Unit)?
    private val simple_Agile: ((SimpleTClass) -> Unit)?
    private val simple_UITheme: ((SimpleTUITheme) -> SimpleTUITheme)?

    constructor(
        simple_Init: ((SimpleTClass) -> Unit)?,
        simple_Start: ((SimpleTClass) -> Unit)?,
        simple_PreLoad: ((SimpleTClass) -> Unit)?,
        simple_Agile: ((SimpleTClass) -> Unit)?,
        simple_UITheme: ((SimpleTUITheme) -> SimpleTUITheme)?
    ) {
        this.simple_Init = simple_Init
        this.simple_Start = simple_Start
        this.simple_PreLoad = simple_PreLoad
        this.simple_Agile = simple_Agile
        this.simple_UITheme = simple_UITheme
    }

    // ======================
    // = SimpleAgileFactory =
    // ======================

    override fun simpleInit(thisClass: SimpleTClass) {
        simple_Init?.invoke(thisClass)
    }

    override fun simpleStart(thisClass: SimpleTClass) {
        simple_Start?.invoke(thisClass)
    }

    override fun simplePreLoad(thisClass: SimpleTClass) {
        simple_PreLoad?.invoke(thisClass)
    }

    override fun simpleAgile(thisClass: SimpleTClass) {
        simple_Agile?.invoke(thisClass)
    }

    override fun simpleUITheme(uiTheme: SimpleTUITheme): SimpleTUITheme {
        return simple_UITheme?.invoke(uiTheme) ?: uiTheme
    }
}