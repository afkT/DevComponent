package afkt_replace.module.tv

import afkt_replace.core.base.repository.NetworkBoundScopeResource
import afkt_replace.core.base.repository.Resource
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.module.tv.data.api.TvAPI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.base.DevPage

class TvRepository {

    /**
     * 请求热门剧集列表
     * @param viewModel ViewModel
     * @param page 请求页数
     * @param devPage DevPage<*>
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<PopularTv>>
     */
    fun requestPopularTv(
        viewModel: ViewModel,
        page: Int,
        devPage: DevPage<*>,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<afkt_replace.core.project.bean.tv.PopularTv>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.tv.PopularTv>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.tv.PopularTv? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.tv.PopularTv?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.tv.PopularTv?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.tv.PopularTv>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.tv.PopularTv) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.tv.PopularTv?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.tv.PopularTv {
                return TvAPI.api().getPopularTv(page)
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
     * 请求剧集详情数据
     * @param viewModel ViewModel
     * @param tvId 剧集 id
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<TvDetails>>
     */
    fun requestTvDetails(
        viewModel: ViewModel,
        tvId: Int,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<afkt_replace.core.project.bean.tv.TvDetails>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.tv.TvDetails>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.tv.TvDetails? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.tv.TvDetails?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.tv.TvDetails?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.tv.TvDetails>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.tv.TvDetails) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.tv.TvDetails?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.tv.TvDetails {
                return TvAPI.api().getTvDetails(tvId)
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