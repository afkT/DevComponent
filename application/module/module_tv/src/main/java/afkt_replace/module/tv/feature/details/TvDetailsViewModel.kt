package afkt_replace.module.tv.feature.details

import afkt_replace.core.base.app.BaseViewModel
import afkt_replace.core.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.base.repository.AbsentLiveData
import afkt_replace.core.base.repository.Resource
import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.base.split.inter.FunctionFlowCall
import afkt_replace.core.config.ParamConst
import afkt_replace.core.databinding.CoreUiBaseStatusBarBinding
import afkt_replace.core.databinding.CoreUiBaseTitleBarBinding
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageTitleBindable
import afkt_replace.core.ui.widget.view_assist.loading_skeleton.PageTitleLoadingSkeletonViewAssist
import afkt_replace.module.tv.TvRepository
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import dev.utils.common.ConvertUtils
import dev.utils.common.able.Getable

class TvDetailsViewModel(
    private val repository: TvRepository = TvRepository()
) : BaseViewModel() {

    // 跳转传参
    private val intentData = IntentData.with()

    // Loading Skeleton 控制封装
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>> = Getable.Get {
        return@Get loadingSkeletonController.value
    }

    // 数据消费处理
    private val dataConsumer = TvDetailsDataConsumerIMPL(
        loadingSkeletonGet, intentData
    )

    // ==========
    // = 请求方法 =
    // ==========

    // ==========
    // = 剧集详情 =
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

    // 请求剧集详情数据
    private val tvIdLiveData: MutableLiveData<Int> = MutableLiveData()
    private val tvDetailsLiveData: LiveData<Resource<afkt_replace.core.project.bean.tv.TvDetails>> =
        tvIdLiveData.switchMap {
            tvIdLiveData.value?.let { tvId ->
                repository.requestTvDetails(
                    viewModel = this, tvId = tvId, flowCall = flowCall
                )
            } ?: AbsentLiveData.create()
        }

    /**
     * 请求剧集详情
     */
    private fun requestTvDetails() {
        tvIdLiveData.postValue(
            ConvertUtils.toInt(intentData.get(ParamConst.TV_ID))
        )
    }

    // ==============
    // = initialize =
    // ==============

    /**
     * 初始化 TvDetailsFragment ViewModel 调用
     * @param fragment TvDetailsFragment
     */
    fun initializeTvDetailsFragment(fragment: TvDetailsFragment) {
        intentData.reader(fragment.arguments)
        // 注册 Loading 骨架 type
        fragment.loadingSkeletonFactory.register(
            PageTitleLoadingSkeletonViewAssist(
                fragment.loadingSkeletonController.viewAssist,
                fragment.loadingSkeletonController.contentAssist.contentLinear,
                reload = { requestTvDetails() },
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
        dataConsumer.initialize(fragment)
        // 监听数据
        tvDetailsLiveData.observe(fragment) {
            bindTvDetailsResource(
                tvDetailsLiveData.value, dataConsumer
            )
        }
        // 请求剧集详情数据
        requestTvDetails()
    }
}