package afkt_replace.module.movie

import afkt_replace.core.base.repository.NetworkBoundScopeResource
import afkt_replace.core.base.repository.Resource
import afkt_replace.core.base.split.inter.FunctionFlowCall
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
    ): LiveData<Resource<afkt_replace.core.project.bean.movie.PopularMovie>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.movie.PopularMovie>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.movie.PopularMovie? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.movie.PopularMovie?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.movie.PopularMovie?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.movie.PopularMovie>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.movie.PopularMovie) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.movie.PopularMovie?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.movie.PopularMovie {
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
    ): LiveData<Resource<afkt_replace.core.project.bean.movie.MovieDetails>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.movie.MovieDetails>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.movie.MovieDetails? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.movie.MovieDetails?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.movie.MovieDetails?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.movie.MovieDetails>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.movie.MovieDetails) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.movie.MovieDetails?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.movie.MovieDetails {
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
    ): LiveData<Resource<afkt_replace.core.project.bean.movie.MoviePosterImages>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.movie.MoviePosterImages>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.movie.MoviePosterImages? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.movie.MoviePosterImages?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.movie.MoviePosterImages?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.movie.MoviePosterImages>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.movie.MoviePosterImages) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.movie.MoviePosterImages?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.movie.MoviePosterImages {
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