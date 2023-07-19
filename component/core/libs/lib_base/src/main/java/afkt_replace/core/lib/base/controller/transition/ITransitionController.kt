package afkt_replace.core.lib.base.controller.transition

/**
 * detail: Base Activity Transition Animation 控制接口
 * @author Ttt
 */
interface ITransitionController {

    /**
     * 是否使用 TransformationLayout 库跳转动画
     * @return `true` yes, `false` no
     */
    fun isUseTransformationLayout(): Boolean = false

    /**
     * 是否设置退出回调, 以实现跳转动画过渡
     * @return `true` yes, `false` no
     */
    fun isTransformationStartContainer(): Boolean = true

    /**
     * 是否设置进入回调, 以实现跳转动画过渡
     * @return `true` yes, `false` no
     */
    fun isTransformationEndContainer(): Boolean = true
}