package afkt_replace.core.base.controller.viewmodel

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.base.split.data.IntentData
import androidx.databinding.ObservableField

/**
 * detail: IntentData ViewModel
 * @author Ttt
 * 注意如果 Activity、Fragment 创建同一个 ViewModel 会导致对象复用、数据覆盖
 * 需自行根据场景设计使用
 */
open class IntentDataViewModel : afkt_replace.core.base.app.BaseViewModel() {

    // Intent 传参读写辅助类
    val intentData = ObservableField<IntentData>()
}