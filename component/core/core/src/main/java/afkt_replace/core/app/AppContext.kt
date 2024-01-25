package afkt_replace.core.app

import afkt_replace.core.base.core.AppChannel
import afkt_replace.core.base.core.AppDebug
import afkt_replace.core.base.core.BaseAppContext
import afkt_replace.core.property.Bugly
import afkt_replace.core.property.BuglyConfig
import afkt_replace.core.property.FloatingDebug
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.alibaba.android.arouter.launcher.ARouter
import dev.DevUtils
import dev.base.utils.assist.DevBaseViewModelAssist

/**
 * detail: App Base Application
 * @author Ttt
 * 使用全局 ViewModel 则需要 Application implements ViewModelStoreOwner
 * 可通过 [DevBaseViewModelAssist.getAppViewModel] 获取
 */
open class AppContext : BaseAppContext(),
    ViewModelStoreOwner {

    override fun onCreate() {
        super.onCreate()
        // 全局静态 Application
        application = this

        if (AppDebug.isOpenDebug()) {
            ARouter.openLog()
            ARouter.openDebug()
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace()
        }
        // 尽可能的早调用, 推荐在 Application 中初始化
        ARouter.init(this)

        // 全局 ViewModel
        mAppViewModelStore = ViewModelStore()

        // Bugly
        getBuglyConfig()?.let { config ->
            Bugly.initialize(this, config)
        }
        // Debug 悬浮窗处理
        FloatingDebug.initialize(this)
    }

    // ==========
    // = 重写方法 =
    // ==========

    // 获取 Bugly 配置
    open fun getBuglyConfig(): BuglyConfig? = defaultBuglyConfig()

    /**
     * 返回默认 Bugly 配置
     */
    private fun defaultBuglyConfig(): BuglyConfig? {
        if (AppChannel.isNotFoundChannel()) return null
        return BuglyConfig(
            key = AppChannel.getChannelInfo(Bugly.KEY) ?: "",
            debug = AppDebug.isOpenDebug(),
            channel = AppChannel.getChannel()
        )
    }

    // ==========
    // = 静态方法 =
    // ==========

    companion object {

        // 全局 Application
        private lateinit var application: AppContext

        // DevBase ViewModel 辅助类
        private val mViewModelAssist = DevBaseViewModelAssist()

        fun application(): AppContext {
            return application
        }

        /**
         * 获取全局 Context
         * @return Context
         */
        fun context(): Context {
            return DevUtils.getContext()
        }

        /**
         * 获取 Context ( 判断 null, 视情况返回全局 Context)
         * @param context Context
         * @return Context
         */
        fun context(context: Context?): Context {
            return DevUtils.getContext(context)
        }

        /**
         * 获取 DevBase ViewModel 辅助类
         * @return DevBaseViewModelAssist
         */
        fun getViewModelAssist(): DevBaseViewModelAssist {
            return mViewModelAssist
        }

        /**
         * 获取 Application ViewModel
         * @param modelClass [ViewModel]
         * @return Application ViewModel
         */
        fun <T : ViewModel> getAppViewModel(modelClass: Class<T>): T? {
            return mViewModelAssist.getAppViewModel(application, modelClass)
        }
    }

    // =======================
    // = ViewModelStoreOwner =
    // =======================

    // ViewModelStore
    private lateinit var mAppViewModelStore: ViewModelStore

    override val viewModelStore: ViewModelStore
        get() = mAppViewModelStore
}