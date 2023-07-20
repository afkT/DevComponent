package afkt_replace.core.base.controller

import afkt_replace.core.base.split.data.IntentData
import android.os.Bundle

/**
 * detail: 基础 Data 控制封装
 * @author Ttt
 */
class BaseDataController(
    val extras: Bundle?
) {
    // Intent 传参读写辅助类
    val intentData: IntentData by lazy {
        IntentData.with(extras)
    }
}