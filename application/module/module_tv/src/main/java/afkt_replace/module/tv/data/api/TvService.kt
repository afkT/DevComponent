package afkt_replace.module.tv.data.api

import afkt_replace.core.project.bean.tv.PopularTv
import afkt_replace.core.project.bean.tv.TvDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * detail: Tv API Service
 * @author Ttt
 */
interface TvService {

    @GET("/3/tv/popular")
    suspend fun getPopularTv(@Query("page") page: Int): afkt_replace.core.project.bean.tv.PopularTv

    @GET("/3/tv/{tv_id}")
    suspend fun getTvDetails(@Path("tv_id") id: Int): afkt_replace.core.project.bean.tv.TvDetails
}