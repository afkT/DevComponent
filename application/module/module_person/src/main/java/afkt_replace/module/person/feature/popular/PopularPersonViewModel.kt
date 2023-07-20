package afkt_replace.module.person.feature.popular

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.base.repository.AbsentLiveData
import afkt_replace.core.base.repository.Resource
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.core.project.bean.person.PopularPerson
import afkt_replace.core.project.bean.person.TMDBPerson
import afkt_replace.core.ui.widget.extension.smartRefreshLoadMoreListener
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonViewAssist
import afkt_replace.lib.tmdb.ui.adapter.PersonProfileItem
import afkt_replace.module.person.PersonNavBuild.routerPersonDetails
import afkt_replace.module.person.PersonRepository
import androidx.lifecycle.*
import dev.base.state.RequestState
import dev.mvvm.command.BindingClick
import dev.utils.common.able.Getable

/**
 * detail: 热门人物 ViewModel
 * @author Ttt
 * 也可统一放到 PersonViewModel, 拆分只是为了更加方便维护管理
 */
class PopularPersonViewModel(
    private val repository: PersonRepository = PersonRepository()
) : BaseViewModel() {

    // 热门人物 Adapter Item
    val popularItem = PersonProfileItem(Getable.Get {
        return@Get uiController.value?.appThemeRes
    }).apply {
        itemClick = object : BindingClick<TMDBPerson> {
            override fun onClick(value: TMDBPerson) {
                value.routerPersonDetails()
            }
        }
    }

    // ==========
    // = 生命周期 =
    // ==========

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // 请求热门人物列表
        resumePopularPerson()
    }

    // ==========
    // = 请求方法 =
    // ==========

    // onResume 请求状态控制
    private val resumeRequest = RequestState<Any>()

    // ==============
    // = 热门人物列表 =
    // ==============

    // Loading Skeleton 控制封装
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 请求页数信息
    private val personPageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val personListLiveData: LiveData<Resource<PopularPerson>> =
        personPageLiveData.switchMap {
            personPageLiveData.value?.let { page ->
                repository.requestPopularPerson(
                    viewModel = this, page = page,
                    devPage = popularItem.page,
                    flowCall = object : FunctionFlowCall {
                        override fun start() {
                            resumeRequest.setRequestIng()
                            loadingSkeletonGet.get()?.showIng()
                        }

                        override fun success() {
                            loadingSkeletonGet.get()?.showSuccess()
                        }

                        override fun error() {
                            loadingSkeletonGet.get()?.showFailed()
                        }

                        override fun finish() {
                            resumeRequest.setRequestNormal()
                        }
                    }
                )
            } ?: AbsentLiveData.create()
        }

    /**
     * 请求热门人物列表
     */
    private fun requestPopularPerson(refresh: Boolean) {
        val page = if (refresh) {
            popularItem.page.configPage
        } else {
            popularItem.page.nextPage
        }
        personPageLiveData.postValue(page)
    }

    /**
     * 请求热门人物列表
     * 内部校验是否存在数据
     */
    private fun resumePopularPerson() {
        if (popularItem.items.isEmpty() && resumeRequest.isRequestNormal) {
            requestPopularPerson(true)
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 PopularPersonFragment ViewModel 调用
     * @param fragment PopularPersonFragment
     */
    fun initializePopularPersonFragment(fragment: PopularPersonFragment) {
        fragment.binding.apply {
            // 设置刷新和加载监听器
            vidRefresh.smartRefreshLoadMoreListener { _, refresh ->
                requestPopularPerson(refresh)
            }
            // 监听数据
            personListLiveData.observe(fragment, Observer {
                it.bindResource(popularItem, vidRefresh)
            })
        }
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear
            ) { requestPopularPerson(true) }
        )
    }
}