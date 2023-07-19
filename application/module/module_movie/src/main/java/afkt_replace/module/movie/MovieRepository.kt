package afkt_replace.module.movie

import afkt_replace.core.lib.base.repository.NetworkBoundScopeResource
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.lib.base.split.inter.FunctionFlowCall
import afkt_replace.core.lib.bean.movie.MovieDetails
import afkt_replace.core.lib.bean.movie.MoviePosterImages
import afkt_replace.core.lib.bean.movie.PopularMovie
import afkt_replace.module.movie.data.api.MovieAPI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.base.DevPage

class MovieRepository {

    /**
     * 请求热门电影列表
     * @param viewModel ViewModel
     * @param page 请求页数
     * @param devPage DevPage<*>
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<PopularMovie>>
     */
    fun requestPopularMovie(
        viewModel: ViewModel,
        page: Int,
        devPage: DevPage<*>,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<PopularMovie>> {
        return object : NetworkBoundScopeResource<PopularMovie>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: PopularMovie? = null

            override fun shouldFetch(data: PopularMovie?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<PopularMovie?> {
                // 从数据库中读取
                val local = MutableLiveData<PopularMovie>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: PopularMovie) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: PopularMovie?
            ) {
            }

            override suspend fun fetchRequest(): PopularMovie {
                return MovieAPI.api().getPopularMovie(page)
            }

            override fun fetchRandomUUID(): Long {
                return devPage.randomTokenUUID()
            }

            override fun shouldFetchByUUID(uuid: Long): Boolean {
                return devPage.equalsTokenUUID(uuid)
            }
        }.asLiveData()
    }

    /**
     * 请求电影详情数据
     * @param viewModel ViewModel
     * @param movieId 电影 id
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<MovieDetails>>
     */
    fun requestMovieDetails(
        viewModel: ViewModel,
        movieId: Int,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<MovieDetails>> {
        return object : NetworkBoundScopeResource<MovieDetails>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: MovieDetails? = null

            override fun shouldFetch(data: MovieDetails?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<MovieDetails?> {
                // 从数据库中读取
                val local = MutableLiveData<MovieDetails>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: MovieDetails) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: MovieDetails?
            ) {
            }

            override suspend fun fetchRequest(): MovieDetails {
                return MovieAPI.api().getMovieDetails(movieId)
            }

            override fun fetchRandomUUID(): Long {
                return 0L
            }

            override fun shouldFetchByUUID(uuid: Long): Boolean {
                return true
            }
        }.asLiveData()
    }

    /**
     * 请求电影海报图片数据
     * @param viewModel ViewModel
     * @param movieId 电影 id
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<MoviePosterImages>>
     */
    fun requestMoviePosterImages(
        viewModel: ViewModel,
        movieId: Int,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<MoviePosterImages>> {
        return object : NetworkBoundScopeResource<MoviePosterImages>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: MoviePosterImages? = null

            override fun shouldFetch(data: MoviePosterImages?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<MoviePosterImages?> {
                // 从数据库中读取
                val local = MutableLiveData<MoviePosterImages>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: MoviePosterImages) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: MoviePosterImages?
            ) {
            }

            override suspend fun fetchRequest(): MoviePosterImages {
                return MovieAPI.api().getMoviePosterImages(movieId)
            }

            override fun fetchRandomUUID(): Long {
                return 0L
            }

            override fun shouldFetchByUUID(uuid: Long): Boolean {
                return true
            }
        }.asLiveData()
    }
}