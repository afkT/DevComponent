package afkt_replace.module.movie.data.api

import afkt_replace.core.app.AppContext
import afkt_replace.core.network.HttpCoreUtils
import afkt_replace.module.movie.BuildConfig
import dev.DevHttpManager
import dev.environment.DevEnvironment
import dev.http.manager.RetrofitBuilder
import dev.http.manager.RetrofitOperation
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * detail: Movie API
 * @author Ttt
 */
class MovieAPI private constructor() {

    companion object {

        private val instance: MovieAPI by lazy { MovieAPI() }

        fun api(): MovieService {
            return instance.api()
        }

        fun operation(): RetrofitOperation {
            return instance.operation()
        }
    }

    // ================
    // = MovieService =
    // ================

    @Volatile
    private var mMovieService: MovieService? = null

    fun api(): MovieService {
        if (mMovieService == null) {
            synchronized(MovieService::class.java) {
                if (mMovieService == null) {
                    createAPI()
                }
            }
        }
        return mMovieService as MovieService
    }

    private fun createAPI() {
        mMovieService = operation().create(
            MovieService::class.java
        )
    }

    // ==================
    // = DevEnvironment =
    // ==================

    private fun apiBaseUrl(): HttpUrl {
        return DevEnvironment.getMovieEnvironmentValue(
            AppContext.context()
        ).toHttpUrl()
    }

    // =====================
    // = RetrofitOperation =
    // =====================

    /**
     * 对外提供操作对象
     * @return RetrofitOperation
     */
    fun operation(): RetrofitOperation {
        return mOperation
    }

    // Retrofit Operation
    private val mOperation: RetrofitOperation by lazy {
        DevHttpManager.RM.putRetrofitBuilder(
            BuildConfig.MODULE_NAME, mRetrofitBuilder
        )
    }

    // ===================
    // = RetrofitBuilder =
    // ===================

    // Retrofit Builder 接口
    private val mRetrofitBuilder: RetrofitBuilder by lazy {
        object : RetrofitBuilder {

            /**
             * 创建 Retrofit Builder
             * @param oldRetrofit 上一次构建的 Retrofit
             * @param httpUrl 构建使用指定 baseUrl
             * @param okHttp OkHttpClient 构建全局复用
             * @return Retrofit.Builder
             */
            override fun createRetrofitBuilder(
                oldRetrofit: Retrofit?,
                httpUrl: HttpUrl?,
                okHttp: OkHttpClient.Builder?
            ): Retrofit.Builder {
                return HttpCoreUtils.createRetrofitBuilder(
                    httpUrl = httpUrl ?: apiBaseUrl(),
                    okHttp = okHttp ?: OkHttpClient.Builder()
                )
            }

            // ==========
            // = 通知事件 =
            // ==========

            /**
             * 重新构建前调用
             * @param key String
             * @param oldRetrofit 上一次构建的 Retrofit
             * 在 [createRetrofitBuilder] 之前调用
             */
            override fun onResetBefore(
                key: String,
                oldRetrofit: Retrofit?
            ) {

            }

            /**
             * 重新构建后调用
             * @param key String
             * @param newRetrofit 重新构建的 Retrofit 对象
             * 在 [createRetrofitBuilder] 之后调用
             */
            override fun onReset(
                key: String,
                newRetrofit: Retrofit?
            ) {
                // 重新构建后创建新的代理对象
                createAPI()
            }
        }
    }
}