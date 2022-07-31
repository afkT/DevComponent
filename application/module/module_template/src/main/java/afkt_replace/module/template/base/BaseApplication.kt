package afkt_replace.module.template.base

import afkt_replace.core.app.AppContext

/**
 * detail: Base Application
 * @author Ttt
 */
class BaseApplication : AppContext() {

    // ==========
    // = 重写方法 =
    // ==========

    override fun onCreate() {
        super.onCreate()

        /**
         * 延迟 1.5 秒模拟初始化耗时白屏效果及解决方案
         * 使用 AppTheme.Launcher style 设置 windowBackground
         * 可使用 layer-list 组合 drawable 做启动底图, 解决使用整张图做底图不同机型拉伸情况
         */
        Thread.sleep(1500L)
    }
}