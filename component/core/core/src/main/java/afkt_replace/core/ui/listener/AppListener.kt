package afkt_replace.core.ui.listener

import android.view.View
import dev.mvvm.command.BindingConsumer

/**
 * detail: APP 通用事件
 * @author Ttt
 */
class AppListener {

    /**
     * 点击返回键
     */
    var onClickBack: BindingConsumer<View>? = null
}