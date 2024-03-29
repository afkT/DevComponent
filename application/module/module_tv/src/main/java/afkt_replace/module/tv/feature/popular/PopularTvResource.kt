package afkt_replace.module.tv.feature.popular

import afkt_replace.core.lib.base.repository.Resource
import afkt_replace.core.lib.base.repository.Status
import afkt_replace.core.lib.bean.tv.PopularTv
import afkt_replace.core.lib.ui.widget.extension.smartFinish
import afkt_replace.core.lib.ui.widget.extension.smartSimpleFinishWithNoMoreData
import afkt_replace.lib.tmdb.ui.adapter.PosterCoverItem
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dev.mvvm.utils.hi.hiif.hiIfNotNull

/**
 * 绑定数据源解析处理
 * @param item PosterCoverItem
 * @param refreshLayout SmartRefreshLayout
 */
fun Resource<PopularTv>.bindResource(
    item: PosterCoverItem,
    refreshLayout: SmartRefreshLayout
) {
    when (status) {
        Status.LOADING -> Unit
        Status.SUCCESS -> {
            data.hiIfNotNull({ popular ->
                item.page.setPage(popular.page).isLastPage = popular.isLastPage()

                if (item.page.isFirstPage) {
                    item.items.clear()
                }
                popular.results?.let { list ->
                    item.items.addAll(list)
                }
                // 智能结束刷新、加载状态并标记是否存在更多数据
                refreshLayout.smartSimpleFinishWithNoMoreData(
                    item.page.isLastPage
                )
            }, {
                // 智能结束刷新、加载状态
                refreshLayout.smartFinish()
            })
        }

        Status.ERROR, Status.EMPTY -> {
            // 智能结束刷新、加载状态
            refreshLayout.smartFinish()
        }
    }
}