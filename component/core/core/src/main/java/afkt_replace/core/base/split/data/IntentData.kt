package afkt_replace.core.base.split.data

import android.content.Intent
import android.os.Bundle
import dev.base.DevIntent
import dev.utils.DevFinal

/**
 * detail: Intent 传参读写辅助类
 * @author Ttt
 * 在 [DevIntent] 基础上新增, 根据 DevFinal.STR 自动生成通用方法
 * 通过 IntentDataKotlinGenerateMain 生成, 自行 copy 代码到该类
 */
class IntentData private constructor() : BaseIntent<IntentData>() {

    // ==========
    // = 静态方法 =
    // ==========

    companion object {

        /**
         * 创建 IntentData
         * @return [IntentData]
         */
        fun with(): IntentData {
            return IntentData()
        }

        /**
         * 创建 IntentData
         * @param intent [Intent]
         * @return [IntentData]
         */
        fun with(intent: Intent?): IntentData {
            return IntentData().reader(intent)
        }

        /**
         * 创建 IntentData
         * @param bundle [Bundle]
         * @return [IntentData]
         */
        fun with(bundle: Bundle?): IntentData {
            return IntentData().reader(bundle)
        }
    }

    // =============
    // = 对外公开方法 =
    // =============

    override fun returnT(): IntentData {
        return this
    }

    // ====================
    // = 以下属于自动生成代码 =
    // ====================

    /**
     * 获取 Key ( DATA ) 对应的 Value
     * @return Value
     */
    fun getData(): String? {
        return get(DevFinal.STR.DATA)
    }

    /**
     * 设置 Key ( DATA ) 对应的 Value
     * @param value 保存的 value
     * @return IntentData
     */
    fun setData(value: String?): IntentData {
        return put(DevFinal.STR.DATA, value)
    }

    /**
     * 移除 Key ( DATA )
     * @return IntentData
     */
    fun removeData(): IntentData {
        return remove(DevFinal.STR.DATA)
    }

    /**
     * 是否存在 Key ( DATA )
     * @return `true` yes, `false` no
     */
    fun containsData(): Boolean {
        return containsKey(DevFinal.STR.DATA)
    }

    /**
     * Key ( DATA ) 保存的 Value 是否为 null
     * @return `true` yes, `false` no
     */
    fun isNullValueData(): Boolean {
        return isNullValue(DevFinal.STR.DATA)
    }

    // =

    /**
     * 获取 Key ( ADS ) 对应的 Value
     * @return Value
     */
    fun getAds(): String? {
        return get(afkt_replace.core.config.KeyConst.ADS)
    }

    /**
     * 设置 Key ( ADS ) 对应的 Value
     * @param value 保存的 value
     * @return IntentData
     */
    fun setAds(value: String?): IntentData {
        return put(afkt_replace.core.config.KeyConst.ADS, value)
    }

    /**
     * 移除 Key ( ADS )
     * @return IntentData
     */
    fun removeAds(): IntentData {
        return remove(afkt_replace.core.config.KeyConst.ADS)
    }

    /**
     * 是否存在 Key ( ADS )
     * @return `true` yes, `false` no
     */
    fun containsAds(): Boolean {
        return containsKey(afkt_replace.core.config.KeyConst.ADS)
    }

    /**
     * Key ( ADS ) 保存的 Value 是否为 null
     * @return `true` yes, `false` no
     */
    fun isNullValueAds(): Boolean {
        return isNullValue(afkt_replace.core.config.KeyConst.ADS)
    }
}