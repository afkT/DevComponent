package afkt_replace.lib.tmdb.ui.adapter

import afkt_replace.core.project.bean.person.KnownFor
import afkt_replace.core.ui.skin.AppThemeRes
import afkt_replace.core.ui.widget.BaseImageView
import afkt_replace.core.project.utils.tmdb.toTMDBImageSource
import afkt_replace.core.project.utils.tmdb.toTMDBImageSourceEmpty
import afkt_replace.lib.tmdb.ui.BR
import afkt_replace.lib.tmdb.ui.R
import afkt_replace.lib.tmdb.ui.adapter.view_assist.PosterCoverViewAssist
import afkt_replace.lib.tmdb.ui.databinding.IncludePersonActionPopupBinding
import afkt_replace.lib.tmdb.ui.databinding.TmdbPersonActingBinding
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import dev.base.DevSource
import dev.engine.image.listener.DrawableListener
import dev.expand.engine.image.display
import dev.mvvm.command.BindingClick
import dev.utils.common.StringUtils
import dev.utils.common.able.Getable
import dev.widget.assist.ViewAssist
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * detail: 人物参演适配器
 * @author Ttt
 */
class PersonActingAdapter(
    val appThemeRes: Getable.Get<AppThemeRes>,
    val itemClick: BindingClick<afkt_replace.core.project.bean.person.KnownFor>
) : BindingRecyclerViewAdapter<afkt_replace.core.project.bean.person.KnownFor>() {

    private val assist = PosterCoverViewAssist(appThemeRes)

    private var selectable = true

    lateinit var actionPopupBinding: IncludePersonActionPopupBinding

    override fun onBindBinding(
        binding: ViewDataBinding,
        variableId: Int,
        layoutRes: Int,
        position: Int,
        item: afkt_replace.core.project.bean.person.KnownFor
    ) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)

        if (binding is TmdbPersonActingBinding) {
            binding.apply {
                val viewAssist = ViewAssist.wrap(vidPlaceholder)
                assist.registerRecyclerLoading(viewAssist) {
                    loadImage(vidPosterIv, viewAssist, item.poster_path)
                }
                loadImage(vidPosterIv, viewAssist, item.poster_path)

                // ==========
                // = 点击事件 =
                // ==========

                vidTransformat.setOnClickListener {
                    if (selectable) {
                        selectable = false
                        actionPopupBinding.details = item
                        actionPopupBinding.executePendingBindings()
                        actionPopupBinding.root.setOnClickListener {
                            vidTransformat.finishTransform()
                            selectable = true
                        }
                        vidTransformat.bindTargetView(actionPopupBinding.root)
                        vidTransformat.startTransform()
                        // 点击海报跳转
                        actionPopupBinding.vidPosterIv.setOnClickListener {
                            itemClick.onClick(item)
                        }
                        // 加载图片
                        actionPopupBinding.vidPosterIv.display(
                            source = item.poster_path.toTMDBImageSourceEmpty()
                        )
                    }
                }
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
 * detail: 人物参演 Item
 * @author Ttt
 */
class PersonActingItem(
    appThemeRes: Getable.Get<AppThemeRes>
) {

    // 适配器
    val adapter = PersonActingAdapter(appThemeRes, object : BindingClick<afkt_replace.core.project.bean.person.KnownFor> {
        override fun onClick(value: afkt_replace.core.project.bean.person.KnownFor) {
            itemClick?.onClick(value)
        }
    })

    // 数据源
    val items = ObservableArrayList<afkt_replace.core.project.bean.person.KnownFor>()

    // Item 点击事件
    var itemClick: BindingClick<afkt_replace.core.project.bean.person.KnownFor>? = null

    // Item Binding
    val itemBinding = ItemBinding.of<afkt_replace.core.project.bean.person.KnownFor>(
        BR.itemValue, R.layout.tmdb_person_acting
    )
}