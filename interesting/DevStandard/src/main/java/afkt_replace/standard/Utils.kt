package afkt_replace.standard

import com.google.gson.GsonBuilder

/**
 * detail: 内部工具类
 * @author Ttt
 */
object Utils {

    // ========
    // = Gson =
    // ========

    /**
     * 创建 GsonBuilder
     * @param serializeNulls 是否序列化null值
     * @return [GsonBuilder]
     */
    private fun createGson(serializeNulls: Boolean): GsonBuilder {
        val builder = GsonBuilder()
        if (serializeNulls) builder.serializeNulls()
        return builder
    }

    /**
     * 转换 JSON 格式数据, 并且格式化
     * @param data         待转换对象
     * @param includeNulls 是否序列化null值
     * @return 格式化 JSON 数据
     */
    fun toJsonFormat(
        data: Any?,
        includeNulls: Boolean
    ): String {
        if (data != null) {
            try {
                // 返回 JSON格式数据 - 格式化
                return createGson(includeNulls)
                    .setPrettyPrinting().create().toJson(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return ""
    }
}