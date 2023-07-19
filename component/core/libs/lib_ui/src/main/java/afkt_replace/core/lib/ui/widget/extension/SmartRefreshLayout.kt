package afkt_replace.core.lib.ui.widget.extension

import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

// ===============================
// = SmartRefreshLayout 库方法封装 =
// ===============================

/**
 * 智能结束刷新、加载状态
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartFinish(): SmartRefreshLayout {
    if (isRefreshing) {
        finishRefresh()
    } else if (isLoading) {
        finishLoadMore()
    }
    return this
}

/**
 * 智能结束刷新、加载状态并标记是否存在更多数据
 * @param noMoreData `true` 没有更多数据, `false` 还有下一页数据
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartFinishWithNoMoreData(noMoreData: Boolean): SmartRefreshLayout {
    if (noMoreData) {
        if (isRefreshing) {
            finishRefreshWithNoMoreData()
        } else if (isLoading) {
            finishLoadMoreWithNoMoreData()
        }
    } else {
        if (isRefreshing) {
            finishRefresh()
        } else if (isLoading) {
            finishLoadMore()
        }
    }
    return this
}

/**
 * 设置是否启用下拉刷新
 * @param enable Boolean
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartEnableRefresh(enable: Boolean = true): SmartRefreshLayout {
    setEnableRefresh(enable)
    return this
}

/**
 * 设置是否启用上拉加载更多
 * @param enable Boolean
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartEnableLoadMore(enable: Boolean = true): SmartRefreshLayout {
    setEnableLoadMore(enable)
    return this
}

/**
 * 设置是否监听列表在滚动到底部时触发加载事件
 * @param enable Boolean
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartEnableAutoLoadMore(enable: Boolean = true): SmartRefreshLayout {
    setEnableAutoLoadMore(enable)
    return this
}

/**
 * 设置是否启用上拉加载更多、监听列表在滚动到底部时触发加载事件
 * @param enable Boolean
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartEnableLoadMoreAll(enable: Boolean = true): SmartRefreshLayout {
    setEnableLoadMore(enable).setEnableAutoLoadMore(enable)
    return this
}

/**
 * 同时设置刷新和加载监听器
 * @param listener (RefreshLayout, Boolean) 是否刷新
 * @return SmartRefreshLayout
 */
fun SmartRefreshLayout.smartRefreshLoadMoreListener(
    listener: ((RefreshLayout, Boolean) -> Unit)
): SmartRefreshLayout {
    setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            listener.invoke(refreshLayout, true)
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            listener.invoke(refreshLayout, false)
        }
    })
    return this
}

// ==============
// = 简化快捷方法 =
// ==============

/**
 * 简化结束刷新、加载状态操作及加载更多控制
 * @param noMoreData Boolean
 * @param enableLoadMore Boolean
 * @return SmartRefreshLayout
 * 常用于默认不允许加载更多, 请求成功一次后知道页数信息再进行控制
 */
fun SmartRefreshLayout.smartSimpleFinishWithNoMoreData(
    noMoreData: Boolean,
    enableLoadMore: Boolean = true
): SmartRefreshLayout {
    return smartEnableLoadMoreAll(enableLoadMore).smartFinishWithNoMoreData(noMoreData)
}