package afkt_replace.core.lib.ui.listener

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

/**
 * detail: APP UI 通用控制
 * @author Ttt
 */
class AppUI {

    // 是否显示返回键
    val backVisible = ObservableBoolean(true)

    // 是否显示标题
    val titleVisible = ObservableBoolean(true)

    // 标题
    val title = ObservableField<String>()

    // 标题是否加粗
    val titleBold = ObservableBoolean(true)
}