package afkt_replace.core.lib.debug.floating

import afkt_replace.core.lib.debug.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import dev.utils.app.assist.floating.IFloatingTouch

/**
 * detail: 悬浮 View
 * @author Ttt
 */
@SuppressLint("ViewConstructor")
internal class FloatingView(
    thisContext: Context,
    private val assist: IFloatingTouch?
) : LinearLayout(thisContext) {

    init {
        initialize()
    }

    private fun initialize() {
        View.inflate(context, R.layout.core_debug_assist_layout_floating, this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        assist?.onTouchEvent(this, event)
        return true
    }
}