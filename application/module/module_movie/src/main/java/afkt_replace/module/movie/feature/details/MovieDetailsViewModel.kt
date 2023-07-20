package afkt_replace.module.movie.feature.details

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.repository.AbsentLiveData
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.core.lib.bean.movie.MovieDetails
import afkt_replace.core.lib.bean.movie.MoviePosterImages
import afkt_replace.core.config.ParamConst
import afkt_replace.core.lib.ui.databinding.CoreUiBaseStatusBarBinding
import afkt_replace.core.lib.ui.databinding.CoreUiBaseTitleBarBinding
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageTitleBindable
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageTitleLoadingSkeletonViewAssist
import afkt_replace.lib.tmdb.ui.adapter.MoviePosterClickConsumer
import afkt_replace.lib.tmdb.ui.adapter.MoviePosterItem
import afkt_replace.module.movie.MovieRepository
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.luck.picture.lib.basic.PictureSelectionPreviewModel
import com.luck.picture.lib.basic.PictureSelector
import dev.engine.media.MediaConfig
import dev.engine.media.MediaData
import dev.expand.engine.media.media_openPreview
import dev.kotlin.utils.image.LuckImageEngineImpl
import dev.utils.common.ConvertUtils
import dev.utils.common.able.Getable

class MovieDetailsViewModel(
    private val repository: MovieRepository = MovieRepository()
) : afkt_replace.core.base.app.BaseViewModel() {

    // 跳转传参
    private val intentData = IntentData.with()

    // Loading Skeleton 控制封装
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 数据消费处理
    private val dataConsumer = MovieDetailsDataConsumerIMPL(
        loadingSkeletonGet, intentData
    )

    // 电影海报 Adapter Item
    val moviePosterItem = MoviePosterItem(Getable.Get {
        return@Get null
    })

    // ==========
    // = 请求方法 =
    // ==========

    // ==========
    // = 电影详情 =
    // ==========

    // 请求方法流程回调
    private val flowCall = object : FunctionFlowCall {
        override fun start() {
            loadingSkeletonGet.get()?.let {
                if (!it.isTypeIng()) it.showIng()
            }
        }

        override fun error() {
            loadingSkeletonGet.get()?.showFailed()
        }
    }

    // 请求电影详情数据
    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val movieDetailsLiveData: LiveData<Resource<MovieDetails>> =
        movieIdLiveData.switchMap {
            movieIdLiveData.value?.let { movieId ->
                repository.requestMovieDetails(
                    viewModel = this, movieId = movieId, flowCall = flowCall
                )
            } ?: AbsentLiveData.create()
        }
    private val moviePosterImagesLiveData: LiveData<Resource<MoviePosterImages>> =
        movieIdLiveData.switchMap {
            movieIdLiveData.value?.let { movieId ->
                repository.requestMoviePosterImages(
                    viewModel = this, movieId = movieId, flowCall = flowCall
                )
            } ?: AbsentLiveData.create()
        }

    /**
     * 请求电影详情
     */
    private fun requestMovieDetails() {
        movieIdLiveData.postValue(
            ConvertUtils.toInt(intentData.get(ParamConst.MOVIE_ID))
        )
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 MovieDetailsFragment ViewModel 调用
     * @param fragment MovieDetailsFragment
     */
    fun initializeMovieDetailsFragment(fragment: MovieDetailsFragment) {
        intentData.reader(fragment.arguments)
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageTitleLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear,
                reload = { requestMovieDetails() },
                bindable = object : PageTitleBindable {
                    override fun bind(
                        param: ViewDataBinding,
                        param2: CoreUiBaseStatusBarBinding,
                        param3: CoreUiBaseTitleBarBinding
                    ): Boolean {
                        fragment.uiController.apply {
                            // 绑定 UI 自动更新
                            initializeVDBVariable(param)
                            // 初始化 StatusBar 骨架 View
                            initializeStatusBarSkeletonView(param2)
                            // 初始化 TitleBar 骨架 View
                            initializeTitleBarSkeletonView(param3)
                            // 设置标题
                            appUI.title.set(intentData.get(ParamConst.TITLE))
                            // 设置返回键
                            param3.vidBackCl.setOnClickListener {
                                fragment.finishActivity()
                            }
                        }
                        return true
                    }
                }
            )
        )
        dataConsumer.initialize(fragment, moviePosterItem)
        // 监听数据
        movieDetailsLiveData.observe(fragment) {
            bindMovieDetailsResource(
                movieDetailsLiveData.value, moviePosterImagesLiveData.value,
                dataConsumer
            )
        }
        moviePosterImagesLiveData.observe(fragment) {
            bindMovieDetailsResource(
                movieDetailsLiveData.value, moviePosterImagesLiveData.value,
                dataConsumer
            )
        }
        // 设置海报 Item 点击事件
        moviePosterItem.itemClick = object : MoviePosterClickConsumer {
            override fun accept(
                path: String,
                index: Int,
                list: MutableList<MediaData>
            ): Boolean {
                fragment.media_openPreview(
                    config = MediaConfig().apply {
                        setLibCustomConfig(
                            PictureSelectionPreviewModel(
                                PictureSelector.create(fragment)
                            ).setImageEngine(
                                LuckImageEngineImpl.createEngine()
                            )
                        )
                        setCustomData(index)
                        setMediaDatas(list)
                    }
                )
                return true
            }
        }
        // 请求电影详情数据
        requestMovieDetails()
    }
}