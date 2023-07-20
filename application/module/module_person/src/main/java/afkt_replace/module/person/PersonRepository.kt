package afkt_replace.module.person

import afkt_replace.core.lib.base.repository.NetworkBoundScopeResource
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.core.project.bean.person.PersonActing
import afkt_replace.core.project.bean.person.PersonDetails
import afkt_replace.core.project.bean.person.PopularPerson
import afkt_replace.module.person.data.api.PersonAPI
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.base.DevPage

class PersonRepository {

    /**
     * 请求热门人物列表
     * @param viewModel ViewModel
     * @param page 请求页数
     * @param devPage DevPage<*>
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<PopularPerson>>
     */
    fun requestPopularPerson(
        viewModel: ViewModel,
        page: Int,
        devPage: DevPage<*>,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<afkt_replace.core.project.bean.person.PopularPerson>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.person.PopularPerson>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.person.PopularPerson? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.person.PopularPerson?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.person.PopularPerson?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.person.PopularPerson>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.person.PopularPerson) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.person.PopularPerson?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.person.PopularPerson {
                return PersonAPI.api().getPopularPerson(page)
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
     * 请求人物详情数据
     * @param viewModel ViewModel
     * @param personId 人物 id
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<PersonDetails>>
     */
    fun requestPersonDetails(
        viewModel: ViewModel,
        personId: Int,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<afkt_replace.core.project.bean.person.PersonDetails>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.person.PersonDetails>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.person.PersonDetails? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.person.PersonDetails?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.person.PersonDetails?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.person.PersonDetails>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.person.PersonDetails) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.person.PersonDetails?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.person.PersonDetails {
                return PersonAPI.api().getPersonDetails(personId)
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
     * 请求人物参演作品数据
     * @param viewModel ViewModel
     * @param personId 人物 id
     * @param flowCall 方法流程回调
     * @return LiveData<Resource<PersonActing>>
     */
    fun requestPersonActing(
        viewModel: ViewModel,
        personId: Int,
        flowCall: FunctionFlowCall?
    ): LiveData<Resource<afkt_replace.core.project.bean.person.PersonActing>> {
        return object : NetworkBoundScopeResource<afkt_replace.core.project.bean.person.PersonActing>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: afkt_replace.core.project.bean.person.PersonActing? = null

            override fun shouldFetch(data: afkt_replace.core.project.bean.person.PersonActing?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<afkt_replace.core.project.bean.person.PersonActing?> {
                // 从数据库中读取
                val local = MutableLiveData<afkt_replace.core.project.bean.person.PersonActing>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: afkt_replace.core.project.bean.person.PersonActing) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: afkt_replace.core.project.bean.person.PersonActing?
            ) {
            }

            override suspend fun fetchRequest(): afkt_replace.core.project.bean.person.PersonActing {
                return PersonAPI.api().getPersonActing(personId)
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