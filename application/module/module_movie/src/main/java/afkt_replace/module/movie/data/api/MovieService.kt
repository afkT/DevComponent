package afkt_replace.module.movie.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * detail: Movie API Service
 * @author Ttt
 */
interface MovieService {

    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): afkt_replace.core.project.bean.movie.PopularMovie

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): afkt_replace.core.project.bean.movie.MovieDetails

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMoviePosterImages(@Path("movie_id") id: Int): afkt_replace.core.project.bean.movie.MoviePosterImages
}