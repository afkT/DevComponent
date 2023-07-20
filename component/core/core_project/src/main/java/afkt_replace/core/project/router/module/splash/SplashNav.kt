package afkt_replace.core.project.router.module.splash

import afkt_replace.core.project.router.module.main.MainNav
import afkt_replace.core.router.AppRouter
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: 具体 navigation 类
 * @author Ttt
 * 不封装在对应的 Router 类中, 统一通过 Nav 类进行跳转、获取
 * 方便后续二次封装、统一编辑等处理
 */
object SplashNav {

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 GROUP 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 GROUP ( Module )
     */
    fun build(path: String): Postcard {
        return SplashRouter.build(path)
    }

    // ==========
    // = 跳转方法 =
    // ==========

    /**
     * 模块入口路由跳转
     */
    fun routerMain() {
        build(SplashRouter.PATH_LAUNCHER).navigation()
    }

    fun buildAppMain(): Postcard {
        return MainNav.buildMain()
    }

    // ====================
    // = PATH - IProvider =
    // ====================

    private var splashProvider: ISplashProvider? = null

    /**
     * 获取 SplashProvider
     * @return ISplashProvider?
     */
    fun splashProvider(): ISplashProvider? {
        if (splashProvider == null) {
            splashProvider = AppRouter.navigation(ISplashProvider::class.java)
        }
        return splashProvider
    }
}