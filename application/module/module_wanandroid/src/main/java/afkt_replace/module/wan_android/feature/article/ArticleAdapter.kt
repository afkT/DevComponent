package afkt_replace.module.wan_android.feature.article

import afkt_replace.module.wan_android.R
import afkt_replace.module.wan_android.databinding.WanandroidAdapterArticleBinding
import afkt_replace.module.wan_android.feature.article.model.ListBean
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.ViewGroup
import dev.adapter.DevDataAdapter
import dev.base.adapter.DevBaseViewBindingVH
import dev.base.adapter.newBindingViewHolder
import dev.kotlin.engine.image.display
import dev.kotlin.utils.image.IMAGE_ROUND_3
import dev.kotlin.utils.image.toImageConfig
import dev.kotlin.utils.toSource
import dev.utils.app.AppUtils
import dev.utils.app.ListenerUtils
import dev.utils.app.TextViewUtils
import dev.utils.common.NumberUtils
import dev.utils.common.StringUtils

/**
 * detail: 文章 Adapter
 * @author Ttt
 */
class ArticleAdapter : DevDataAdapter<ListBean, DevBaseViewBindingVH<WanandroidAdapterArticleBinding>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevBaseViewBindingVH<WanandroidAdapterArticleBinding> {
        return newBindingViewHolder(parent, R.layout.wanandroid_adapter_article)
    }

    override fun onBindViewHolder(
        holder: DevBaseViewBindingVH<WanandroidAdapterArticleBinding>,
        position: Int
    ) {
        val item = getDataItem(position)
        // 标题
        TextViewUtils.setHtmlText(holder.binding.vidTitleTv, item.title)
        // 时间
        holder.binding.vidTimeTv.text = StringUtils.checkValue(item.niceShareDate, item.niceDate)
        // 随机图片
        holder.binding.vidPicIv.display(
            source = "https://picsum.photos/2${NumberUtils.addZero(position)}".toSource(),
            config = IMAGE_ROUND_3.toImageConfig()
        )
        // 绑定点击事件
        ListenerUtils.setOnClicks({
            val link = item.link
            if (!TextUtils.isEmpty(link)) {
                val uri = Uri.parse(link)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                AppUtils.startActivity(intent)
            }
        }, holder.binding.vidCv)
    }
}