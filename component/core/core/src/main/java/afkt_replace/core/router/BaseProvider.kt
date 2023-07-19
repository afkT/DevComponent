package afkt_replace.core.router

import com.alibaba.android.arouter.facade.template.IProvider
import dev.utils.LogPrintUtils

/**
 * detail: 各个组件通讯接口
 * @author Ttt
 * Router IProvider 基础 Provider 类, 方便统一初始化控制、打印日志等
 */
interface BaseProvider : IProvider

// 初始化 TAG
const val PROVIDER_INIT_TAG = "Provider_Init"

/**
 * detail: BaseProvider 扩展类
 * @author Ttt
 */
open class BaseProviderExt(TAG: String) {

    init {
        printProviderInitialize(TAG)
    }

    /**
     * 打印 Provider 初始化日志
     * @param tag Module class Name
     */
    private fun printProviderInitialize(tag: String) {
        LogPrintUtils.dTag(PROVIDER_INIT_TAG, "$tag - initialize")
    }
}