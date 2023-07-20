package afkt_replace.module.tv.feature.details

import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.lib.base.repository.Status
import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.project.bean.tv.TvDetails
import afkt_replace.core.config.ParamConst
import afkt_replace.module.tv.databinding.TvFragmentDetailsBinding
import androidx.lifecycle.MutableLiveData
import dev.utils.common.able.Consumerable
import dev.utils.common.able.Getable

interface TvDetailsDataConsumer : Consumerable.ConsumerByParam<
        Boolean, afkt_replace.core.project.bean.tv.TvDetails?>

/**
 * 绑定数据源解析处理
 */
fun bindTvDetailsResource(
    tvDetails: Resource<afkt_replace.core.project.bean.tv.TvDetails>?,
    consumer: TvDetailsDataConsumer
) {
    var details: afkt_replace.core.project.bean.tv.TvDetails? = null
    // 解析剧集详情数据
    tvDetails?.let {
        when (it.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> {
                it.data?.let { bean ->
                    details = bean
                }
            }

            Status.ERROR, Status.EMPTY -> Unit
        }
    }
    consumer.accept(details)
}

/**
 * detail: 剧集详情数据消费实现类
 * @author Ttt
 */
class TvDetailsDataConsumerIMPL(
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>>,
    private val intentData: IntentData
) : TvDetailsDataConsumer {

    private lateinit var binding: TvFragmentDetailsBinding

    override fun accept(
        param: afkt_replace.core.project.bean.tv.TvDetails?
    ): Boolean {
        param?.let {
            details.postValue(it)
        }
        if (param != null) {
            loadingSkeletonGet.get()?.showSuccess(
                notFoundOP = false
            )
        }
        return true
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    fun initialize(fragment: TvDetailsFragment) {
        fragment.binding.let {
            this.binding = it
            it.details = details
        }
        fragment.uiController.apply {
            // 初始化 CoreUiBaseHeaderBinding 通用 include layout
            initializeCoreUiBaseHeader(binding.vidHeader, true)
            // 设置标题
            appUI.title.set(intentData.get(ParamConst.TITLE))
            // 设置返回键
            binding.vidHeader.vidTitleBar.vidBackCl.setOnClickListener {
                fragment.finishActivity()
            }
        }
    }

    // 数据源
    private val details = MutableLiveData<afkt_replace.core.project.bean.tv.TvDetails>()
}