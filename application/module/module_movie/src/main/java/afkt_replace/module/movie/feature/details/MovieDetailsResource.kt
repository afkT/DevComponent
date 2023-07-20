package afkt_replace.module.movie.feature.details

import afkt_replace.core.lib.base.controller.loading.BaseLoadingSkeletonController
import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.lib.base.repository.Status
import afkt_replace.core.base.split.data.IntentData
import afkt_replace.core.project.bean.movie.MovieDetails
import afkt_replace.core.project.bean.movie.MoviePosterImages
import afkt_replace.core.config.ParamConst
import afkt_replace.core.project.utils.tmdb.toTMDBImageSource
import afkt_replace.lib.tmdb.ui.adapter.MoviePosterItem
import afkt_replace.module.movie.databinding.MovieFragmentDetailsBinding
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import dev.engine.media.MediaData
import dev.utils.common.StringUtils
import dev.utils.common.able.Consumerable
import dev.utils.common.able.Getable

interface MovieDetailsDataConsumer : Consumerable.ConsumerByParam2<
        Boolean, afkt_replace.core.project.bean.movie.MovieDetails?, afkt_replace.core.project.bean.movie.MoviePosterImages?>

/**
 * 绑定数据源解析处理
 */
fun bindMovieDetailsResource(
    movieDetails: Resource<afkt_replace.core.project.bean.movie.MovieDetails>?,
    moviePosterImages: Resource<afkt_replace.core.project.bean.movie.MoviePosterImages>?,
    consumer: MovieDetailsDataConsumer
) {
    var details: afkt_replace.core.project.bean.movie.MovieDetails? = null
    var moviePoster: afkt_replace.core.project.bean.movie.MoviePosterImages? = null
    // 解析电影详情数据
    movieDetails?.let {
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
    // 解析电影海报图片数据
    moviePosterImages?.let {
        when (it.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> {
                it.data?.let { bean ->
                    moviePoster = bean
                }
            }

            Status.ERROR, Status.EMPTY -> Unit
        }
    }
    consumer.accept(details, moviePoster)
}

/**
 * detail: 电影详情数据消费实现类
 * @author Ttt
 */
class MovieDetailsDataConsumerIMPL(
    private val loadingSkeletonGet: Getable.Get<BaseLoadingSkeletonController<*>>,
    private val intentData: IntentData
) : MovieDetailsDataConsumer {

    private lateinit var binding: MovieFragmentDetailsBinding

    override fun accept(
        param: afkt_replace.core.project.bean.movie.MovieDetails?,
        param2: afkt_replace.core.project.bean.movie.MoviePosterImages?
    ): Boolean {
        param?.let {
            details.postValue(it)
        }
        param2?.let {
            moviePoster.postValue(it)
        }
        if (param != null && param2 != null) {
            loadingSkeletonGet.get()?.showSuccess(
                notFoundOP = false
            )
        }
        return true
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    fun initialize(
        fragment: MovieDetailsFragment,
        moviePosterItem: MoviePosterItem
    ) {
        fragment.binding.let {
            this.binding = it
            it.details = details
            it.poster = moviePoster
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
        moviePoster.observe(fragment) {
            moviePosterItem.items.clear()
            val repeat = mutableMapOf<String, String>()
            // 移除空的数据
            moviePosterItem.items.addAll(it.posters.mapIndexedNotNull { _, poster ->
                val path = poster.file_path
                if (StringUtils.isNotEmpty(path) && !repeat.containsKey(path)) {
                    repeat[path] = ""
                    moviePosterItem.posterMediaData.add(
                        MediaData().apply {
                            setOriginalUri(Uri.parse(path.toTMDBImageSource().mUrl))
                        }
                    )
                    return@mapIndexedNotNull path
                }
                return@mapIndexedNotNull null
            })
        }
    }

    // 数据源
    private val details = MutableLiveData<afkt_replace.core.project.bean.movie.MovieDetails>()
    private val moviePoster = MutableLiveData<afkt_replace.core.project.bean.movie.MoviePosterImages>()
}