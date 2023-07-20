package afkt_replace.module.person

import afkt_replace.core.base.repository.NetworkBoundScopeResource
import afkt_replace.core.base.repository.Resource
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
    ): LiveData<Resource<PopularPerson>> {
        return object : NetworkBoundScopeResource<PopularPerson>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: PopularPerson? = null

            override fun shouldFetch(data: PopularPerson?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<PopularPerson?> {
                // 从数据库中读取
                val local = MutableLiveData<PopularPerson>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: PopularPerson) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: PopularPerson?
            ) {
            }

            override suspend fun fetchRequest(): PopularPerson {
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
    ): LiveData<Resource<PersonDetails>> {
        return object : NetworkBoundScopeResource<PersonDetails>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: PersonDetails? = null

            override fun shouldFetch(data: PersonDetails?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<PersonDetails?> {
                // 从数据库中读取
                val local = MutableLiveData<PersonDetails>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: PersonDetails) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: PersonDetails?
            ) {
            }

            override suspend fun fetchRequest(): PersonDetails {
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
    ): LiveData<Resource<PersonActing>> {
        return object : NetworkBoundScopeResource<PersonActing>(
            viewModel.viewModelScope, flowCall = flowCall
        ) {
            // 本地数据正常属于查询数据库数据
            var localDBData: PersonActing? = null

            override fun shouldFetch(data: PersonActing?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<PersonActing?> {
                // 从数据库中读取
                val local = MutableLiveData<PersonActing>()
                local.postValue(localDBData)
                return local
            }

            override fun saveFetchData(item: PersonActing) {
                // 存储到本地数据库
                this.localDBData = item
            }

            override fun onFetchFailed(
                error: Throwable?,
                items: PersonActing?
            ) {
            }

            override suspend fun fetchRequest(): PersonActing {
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