package afkt_replace.core.lib.ui.widget

import afkt_replace.core.lib.ui.R
import afkt_replace.core.lib.ui.databinding.CoreUiBaseTitleBarBinding
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import dev.utils.app.ResourceUtils

/**
 * detail: Base Title Bar
 * @author Ttt
 * 通用 Title 封装 View
 */
class BaseTitleBar : LinearLayout {

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

    // =

    val binding: CoreUiBaseTitleBarBinding by lazy {
        CoreUiBaseTitleBarBinding.inflate(LayoutInflater.from(context))
    }

    /**
     * 默认初始化操作
     */
    private fun init() {
        orientation = VERTICAL

        // 添加 View
        addView(
            binding.root,
            FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ResourceUtils.getDimensionInt(R.dimen.dp_45)
            )
        )
    }

    // ===========
    // = get/set =
    // ===========

    fun setTitle(title: String?): BaseTitleBar {
        binding.vidTitleTv.text = title
        return this
    }

    fun setTitle(@StringRes resId: Int): BaseTitleBar {
        return setTitle(ResourceUtils.getString(resId))
    }

    fun setTitleColor(color: Int): BaseTitleBar {
        binding.vidTitleTv.setTextColor(color)
        return this
    }

    fun setBackListener(listener: OnClickListener): BaseTitleBar {
        binding.vidBackIv.setOnClickListener(listener)
        return this
    }

    fun goneBackView(): BaseTitleBar {
        binding.vidBackIv.visibility = GONE
        return this
    }
}