package afkt_replace.core.ui.animation

import afkt_replace.core.config.AppLibConfig
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.ChecksSdkIntAtLeast

// ===================
// = View 动画扩展函数 =
// ===================

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP)
private fun checkIsMaterialVersion() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun View.circularRevealedAtCenter() {
    val view = this
    val cx = (view.left + view.right) / 2
    val cy = (view.top + view.bottom) / 2
    val finalRadius = Math.max(view.width, view.height)

    if (checkIsMaterialVersion() && view.isAttachedToWindow) {
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius.toFloat())
        view.visibility = View.VISIBLE
        view.setBackgroundColor(Color.TRANSPARENT)
        anim.duration = AppLibConfig.ANIM_VIEW_CIRCULAR_DURATION_MILLIS
        anim.start()
    }
}