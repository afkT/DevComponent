package afkt_replace.module.movie.feature.popular

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.repository.AbsentLiveData
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.core.project.bean.base.TMDBCommon
import afkt_replace.core.project.bean.movie.PopularMovie
import afkt_replace.core.ui.widget.extension.smartRefreshLoadMoreListener
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageLoadingSkeletonViewAssist
import afkt_replace.lib.tmdb.ui.adapter.PosterCoverItem
import afkt_replace.module.movie.MovieNavBuild.routerMovieDetails
import afkt_replace.module.movie.MovieRepository
import androidx.lifecycle.*
import dev.base.state.RequestState
import dev.mvvm.command.BindingClick
import dev.utils.common.able.Getable

/**
 * detail: 热门电影 ViewModel
 * @author Ttt
 * 也可统一放到 MovieViewModel, 拆分只是为了更加方便维护管理
 */
class PopularMovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : afkt_replace.core.base.app.BaseViewModel() {

    // 热门电影 Adapter Item
    val popularItem = PosterCoverItem(Getable.Get {
        return@Get uiController.value?.appThemeRes
    }).apply {
        itemClick = object : BindingClick<afkt_replace.core.project.bean.base.TMDBCommon> {
            override fun onClick(value: afkt_replace.core.project.bean.base.TMDBCommon) {
                value.routerMovieDetails()
            }
        }
    }

    // ==========
    // = 生命周期 =
    // ==========

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // 请求热门电影列表
        resumePopularMovie()
    }

    // ==========
    // = 请求方法 =
    // ==========

    // onResume 请求状态控制
    private val resumeRequest = RequestState<Any>()

    // ==============
    // = 热门电影列表 =
    // ==============

    // Loading Skeleton 控制封装
    var loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 请求页数信息
    private val moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    private val movieListLiveData: LiveData<Resource<afkt_replace.core.project.bean.movie.PopularMovie>> = moviePageLiveData.switchMap {
        moviePageLiveData.value?.let { page ->
            repository.requestPopularMovie(
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
     * 请求热门电影列表
     */
    private fun requestPopularMovie(refresh: Boolean) {
        val page = if (refresh) {
            popularItem.page.configPage
        } else {
            popularItem.page.nextPage
        }
        moviePageLiveData.postValue(page)
    }

    /**
     * 请求热门电影列表
     * 内部校验是否存在数据
     */
    private fun resumePopularMovie() {
        if (popularItem.items.isEmpty() && resumeRequest.isRequestNormal) {
            requestPopularMovie(true)
        }
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 PopularMovieFragment ViewModel 调用
     * @param fragment PopularMovieFragment
     */
    fun initializePopularMovieFragment(fragment: PopularMovieFragment) {
        fragment.binding.apply {
            // 设置刷新和加载监听器
            vidRefresh.smartRefreshLoadMoreListener { _, refresh ->
                requestPopularMovie(refresh)
            }
            // 监听数据
            movieListLiveData.observe(fragment, Observer {
                it.bindResource(popularItem, vidRefresh)
            })
        }
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear
            ) { requestPopularMovie(true) }
        )
    }
}