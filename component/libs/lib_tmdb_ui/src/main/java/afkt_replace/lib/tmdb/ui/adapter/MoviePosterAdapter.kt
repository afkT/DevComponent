package afkt_replace.lib.tmdb.ui.adapter

import afkt_replace.core.project.utils.tmdb.toTMDBImageSource
import afkt_replace.core.ui.skin.AppThemeRes
import afkt_replace.core.ui.widget.BaseImageView
import afkt_replace.lib.tmdb.ui.BR
import afkt_replace.lib.tmdb.ui.R
import afkt_replace.lib.tmdb.ui.adapter.view_assist.PosterCoverViewAssist
import afkt_replace.lib.tmdb.ui.databinding.TmdbMoviePosterBinding
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import dev.base.DevSource
import dev.engine.image.listener.DrawableListener
import dev.engine.media.MediaData
import dev.expand.engine.image.display
import dev.mvvm.command.BindingClick
import dev.utils.common.StringUtils
import dev.utils.common.able.Consumerable
import dev.utils.common.able.Getable
import dev.widget.assist.ViewAssist
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

interface MoviePosterClickConsumer : Consumerable.ConsumerByParam3<
        Boolean, String, Int, MutableList<MediaData>>

/**
 * detail: 电影海报适配器
 * @author Ttt
 */
class MoviePosterAdapter(
    val appThemeRes: Getable.Get<AppThemeRes>
) : BindingRecyclerViewAdapter<String>() {

    private val assist = PosterCoverViewAssist(appThemeRes)

    override fun onBindBinding(
        binding: ViewDataBinding,
        variableId: Int,
        layoutRes: Int,
        position: Int,
        item: String
    ) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)

        if (binding is TmdbMoviePosterBinding) {
            binding.apply {
                val viewAssist = ViewAssist.wrap(vidPlaceholder)
                assist.registerRecyclerLoading(viewAssist) {
                    loadImage(vidPosterIv, viewAssist, item)
                }
                loadImage(vidPosterIv, viewAssist, item)

                // ==========
                // = 点击事件 =
                // ==========
            }
        }
    }

    private fun loadImage(
        imageView: BaseImageView,
        viewAssist: ViewAssist,
        url: String?
    ) {
        if (StringUtils.isEmpty(url)) {
            viewAssist.goneWrapper()
            return
        }
        viewAssist.showIng()
        // 加载图片
        imageView.display(
            source = url.toTMDBImageSource(),
            listener = object : DrawableListener() {
                override fun onStart(source: DevSource) {
//                    viewAssist.showIng()
                }

                override fun onResponse(
                    source: DevSource,
                    value: Drawable
                ) {
                    viewAssist.showSuccess()
                }

                override fun onFailure(
                    source: DevSource,
                    throwable: Throwable
                ) {
                    viewAssist.showFailed()
                }
            }
        )
    }
}

/**
 * detail: 电影海报 Item
 * @author Ttt
 */
class MoviePosterItem(
    appThemeRes: Getable.Get<AppThemeRes>
) {

    // 适配器
    val adapter = MoviePosterAdapter(appThemeRes)

    // 数据源
    val items = ObservableArrayList<String>()

    // Item 点击事件
    var itemClick: MoviePosterClickConsumer? = null

    // Item Binding
    val itemBinding = ItemBinding.of<String>(
        BR.itemValue, R.layout.tmdb_movie_poster
    ).bindExtra(
        BR.itemClick, object : BindingClick<String> {
            override fun onClick(value: String) {
                itemClick?.accept(
                    value, items.indexOf(value), posterMediaData
                )
            }
        }
    )

    // 海报预览数据
    val posterMediaData = mutableListOf<MediaData>()
}