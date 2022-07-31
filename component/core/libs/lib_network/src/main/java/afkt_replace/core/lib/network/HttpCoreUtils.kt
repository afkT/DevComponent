package afkt_replace.core.lib.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * detail: Http Core 工具类
 * @author Ttt
 */
object HttpCoreUtils {

    /**
     * 创建 Retrofit Builder
     * @param builder Retrofit Builder ( 可使用历史 [Retrofit.newBuilder] )
     * @param httpUrl HttpUrl
     * @param okHttp Builder
     * @return Retrofit.Builder
     */
    fun createRetrofitBuilder(
        builder: Retrofit.Builder = Retrofit.Builder(),
        httpUrl: HttpUrl,
        okHttp: OkHttpClient.Builder
    ): Retrofit.Builder {
        return builder.apply {
            // 设置 Gson 解析
            addGsonConverterFactory()
            // OkHttpClient
            client(okHttp.build())
            // 服务器地址
            baseUrl(httpUrl)
        }
    }
}

// ==========
// = 快捷方法 =
// ==========

/**
 * 设置 Retrofit Gson 解析
 * @receiver Retrofit.Builder
 * @return Retrofit.Builder
 */
fun Retrofit.Builder.addGsonConverterFactory(): Retrofit.Builder {
    // 设置 Gson 解析
    return addConverterFactory(GsonConverterFactory.create())
}