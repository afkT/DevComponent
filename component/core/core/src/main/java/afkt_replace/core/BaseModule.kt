package afkt_replace.core

import dev.utils.LogPrintUtils

// 初始化 TAG
const val MODULAR_INIT_TAG = "Modular_Init"

/**
 * detail: Base Module ( ContentProvider Initializer )
 * @author Ttt
 * App Startup Initializer 基础 Module 类, 方便统一初始化控制、打印日志等
 */
open class BaseModule(val TAG: String) {

    init {
        printModularInitialize(TAG)
    }

    /**
     * 打印 Modular 初始化日志
     * @param tag Module class Name
     */
    private fun printModularInitialize(tag: String) {
        LogPrintUtils.dTag(MODULAR_INIT_TAG, "$tag - initialize")
    }
}