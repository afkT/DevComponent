package afkt_replace.core.lib.base.split.controller

import android.app.Activity
import android.content.Intent
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer
import com.skydoves.transformationlayout.onTransformationStartContainer

/**
 * detail: TransformationLayout 跳转动画控制器
 * @author Ttt
 */
open class TransformationLayoutController {

    /**
     * 是否设置退出回调, 以实现跳转动画过渡
     * @param activity Activity
     * [TransformationLayout] 库跳转动画使用
     * 在 super.onCreate() 之前调用
     */
    protected fun createBeforeOnTransformationStartContainer(activity: Activity) {
        activity.onTransformationStartContainer()
    }

    /**
     * 是否设置进入回调, 以实现跳转动画过渡
     * @param activity Activity
     * [TransformationLayout] 库跳转动画使用
     * 在 super.onCreate() 之前调用
     */
    protected fun createBeforeOnTransformationEndContainer(activity: Activity) {
        // intent.getParcelableExtra(TransformationCompat.activityTransitionName)
        activity.intent.getParcelableExtra<TransformationLayout.Params>(
            "com.skydoves.transformationlayout"
        )?.let {
            activity.onTransformationEndContainer(it)
        }
    }

    /**
     * 路由跳转动画
     * @param layout TransformationLayout
     * @param intent 跳转信息
     * @return `true` success, `false` fail
     */
    protected fun routerTransition(
        layout: TransformationLayout,
        intent: Intent?
    ): Boolean {
        intent?.let {
            TransformationCompat.startActivity(layout, it)
            return true
        }
        return false
    }
}