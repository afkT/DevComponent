package afkt_replace.core.base.core

import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * detail: App 刷新、加载相关配置
 * @author Ttt
 */
object AppRefresh {

    /**
     * 初始化方法
     */
    fun initialize() {
        // 设置全局的 Header 构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(context)
        }
        // 设置全局的 Footer 构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context)
        }
    }
}