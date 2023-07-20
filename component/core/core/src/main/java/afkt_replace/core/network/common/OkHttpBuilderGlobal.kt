package afkt_replace.core.network.common

import afkt_replace.core.lib.engine.debug.DevDebugEngine
import afkt_replace.core.lib.network.BuildConfig
import afkt_replace.core.network.HttpCoreConst
import afkt_replace.core.project.network.tmdb.TMDBAPIInterceptor
import dev.capture.CallbackInterceptor
import dev.capture.CaptureInfo
import dev.capture.IHttpCaptureEnd
import dev.expand.engine.log.log_jsonTag
import dev.http.manager.OkHttpBuilder
import dev.http.manager.RetrofitBuilder
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * detail: 全局通用 OkHttp Builder
 * @author Ttt
 * 全局 ( 通过 Key 进行特殊化创建 )
 * 可用于 [RetrofitBuilder.createRetrofitBuilder] okHttp 参数传入并创建
 */
class OkHttpBuilderGlobal : OkHttpBuilder {

    /**
     * 创建 OkHttp Builder
     * @param key Key ( RetrofitBuilder Manager Key )
     * @return OkHttpClient.Builder
     */
    override fun createOkHttpBuilder(key: String): OkHttpClient.Builder {
//        // 可搭配监听进度使用
//        return DevHttpManager.PM.getOperation(key)?.wrap(
//            commonOkHttpBuilder(key)
//        )
        return commonOkHttpBuilder(key)
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 通用 OkHttp Builder 创建方法
     * @param key String
     * @return OkHttpClient.Builder
     */
    private fun commonOkHttpBuilder(key: String): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {

            // ==========
            // = 通用配置 =
            // ==========

            // 全局的读取超时时间
            readTimeout(HttpCoreConst.READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            // 全局的写入超时时间
            writeTimeout(HttpCoreConst.WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            // 全局的连接超时时间
            connectTimeout(HttpCoreConst.CONNECT_TIMEOUT_MS, TimeUnit.MILLISECONDS)

            // ========
            // = TMDB =
            // ========

            // TMDB API 参数拦截器
            addInterceptor(afkt_replace.core.project.network.tmdb.TMDBAPIInterceptor())

            // =============
            // = 不同版本构建 =
            // =============

            // 是否 Release 版本标记 ( 用于标记 APK 是否 Release 发布版本 )
            if (BuildConfig.isRelease) {
                releaseOkHttpBuilder(key, this)
            } else {
                debugOkHttpBuilder(key, this)
            }
        }
    }

    // ===========
    // = Release =
    // ===========

    /**
     * release 版本构建 OkHttp Builder
     * @param key String
     * @param builder Builder
     */
    private fun releaseOkHttpBuilder(
        key: String,
        builder: OkHttpClient.Builder
    ) {
        builder.apply {
        }
    }

    // =========
    // = Debug =
    // =========

    /**
     * debug 版本构建 OkHttp Builder
     * @param key String
     * @param builder Builder
     */
    private fun debugOkHttpBuilder(
        key: String,
        builder: OkHttpClient.Builder
    ) {
        builder.apply {
            // Http 抓包拦截器 ( 无存储逻辑, 进行回调通知 )
            addInterceptor(CallbackInterceptor(endCall = object : IHttpCaptureEnd {
                override fun callEnd(info: CaptureInfo) {
                    // 打印 Http 请求信息 log
                    // tag 为 "key"_http_capture
                    val tag = "${key}_http_capture"
                    tag.log_jsonTag(
                        json = info.toJson()
                    )
                }
            }))
            // 添加 Http 抓包拦截处理
            DevDebugEngine.getEngine()?.addInterceptor(
                builder, key
            )
        }
    }
}