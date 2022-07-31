package afkt_replace.core.lib.ui.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import dev.utils.app.ScreenUtils
import dev.utils.app.ViewUtils

/**
 * detail: Base Status Bar
 * @author Ttt
 * 自动设置高度占位状态栏
 */
class BaseStatusBarView : View {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    /**
     * 默认初始化操作
     */
    private fun init() {
        val statusBarHeight: Int = ScreenUtils.getStatusBarHeight()
        ViewUtils.setLayoutParams(
            this, ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight
            )
        )
    }
}