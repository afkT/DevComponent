package afkt_replace.lib.tmdb.ui.adapter

import afkt_replace.core.lib.bean.person.TMDBPerson
import afkt_replace.core.lib.ui.skin.AppThemeRes
import afkt_replace.core.lib.ui.widget.BaseImageView
import afkt_replace.core.lib.utils.toTMDBImageSource
import afkt_replace.lib.tmdb.ui.BR
import afkt_replace.lib.tmdb.ui.R
import afkt_replace.lib.tmdb.ui.adapter.view_assist.PosterCoverViewAssist
import afkt_replace.lib.tmdb.ui.databinding.TmdbPersonProfileBinding
import android.graphics.drawable.Drawable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ViewDataBinding
import dev.base.DevPage
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
 * detail: 人物简介适配器
 * @author Ttt
 */
class PersonProfileAdapter<T : TMDBPerson>(
    val appThemeRes: Getable.Get<AppThemeRes>
) : BindingRecyclerViewAdapter<T>() {

    private val assist = PosterCoverViewAssist(appThemeRes)

    override fun onBindBinding(
        binding: ViewDataBinding,
        variableId: Int,
        layoutRes: Int,
        position: Int,
        item: T
    ) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)

        if (binding is TmdbPersonProfileBinding) {
            binding.apply {
                val viewAssist = ViewAssist.wrap(vidPlaceholder)
                assist.registerRecyclerLoading(viewAssist) {
                    loadImage(vidProfileIv, viewAssist, item.profile_path)
                }
                loadImage(vidProfileIv, viewAssist, item.profile_path)
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
 * detail: 人物简介 Item
 * @author Ttt
 */
class PersonProfileItem(
    appThemeRes: Getable.Get<AppThemeRes>
) {

    // 适配器
    val adapter = PersonProfileAdapter<TMDBPerson>(appThemeRes)

    // 页数信息
    val page = DevPage.getDefault<Any>()

    // 数据源
    val items = ObservableArrayList<TMDBPerson>()

    // Item 点击事件
    var itemClick: BindingClick<TMDBPerson>? = null

    // Item Binding
    val itemBinding = ItemBinding.of<TMDBPerson>(
        BR.itemValue, R.layout.tmdb_person_profile
    ).bindExtra(
        BR.itemClick, object : BindingClick<TMDBPerson> {
            override fun onClick(value: TMDBPerson) {
                itemClick?.onClick(value)
            }
        }
    )
}