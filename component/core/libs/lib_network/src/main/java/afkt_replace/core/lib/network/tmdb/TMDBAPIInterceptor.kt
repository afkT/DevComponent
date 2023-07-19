package afkt_replace.core.lib.network.tmdb

import afkt_replace.core.lib.network.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * detail: TMDB API 参数拦截器
 * @author Ttt
 */
class TMDBAPIInterceptor : Interceptor {

    // https://www.themoviedb.org API Key
    private val TMDB_API_KEY = BuildConfig.TMDB_API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        val oldHttpUrl: HttpUrl = request.url
        val httpUrl = oldHttpUrl.newBuilder().apply {
            addQueryParameter("api_key", TMDB_API_KEY)
            // addQueryParameter language...
        }
        return chain.proceed(builder.url(httpUrl.build()).build())
    }
}