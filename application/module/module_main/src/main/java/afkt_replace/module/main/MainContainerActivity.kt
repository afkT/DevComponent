package afkt_replace.module.main

import afkt_replace.core.lib.base.app.BaseAppActivity
import afkt_replace.core.lib.base.controller.ui.ext.defaultMainContainerController
import afkt_replace.core.lib.base.controller.ui.ext.setExitBackIntercept
import afkt_replace.core.lib.base.controller.ui.theme.defaultMainContainerUITheme
import afkt_replace.core.lib.bean.splash.SplashAds
import afkt_replace.core.lib.config.KeyConst
import afkt_replace.core.lib.router.module.main.MainRouter
import afkt_replace.core.lib.router.module.movie.MovieNav
import afkt_replace.module.main.databinding.MainAppContainerBinding
import afkt_replace.module.main.feature.adapter.MainPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import dev.expand.engine.json.fromJson
import dev.mvvm.utils.toResString

@Route(path = MainRouter.PATH_MAIN, group = MainRouter.GROUP)
class MainContainerActivity : BaseAppActivity<MainAppContainerBinding, MainViewModel>(
    R.layout.main_app_container, BR.viewModel, simple_UITheme = {
        it.defaultMainContainerUITheme()
    }, simple_PreLoad = {
        it.apply {
            uiController.defaultMainContainerController(
                R.string.app_name.toResString()
            )
            // 设置返回键退出 App 拦截监听
            keyEventController.setExitBackIntercept(BuildConfig.MODULE_NAME)
        }
    }
) {

    override fun initView() {
        super.initView()

        if (BuildConfig.isModular) return

        consumerSplashADS()

        with(binding.vidVp) {
            adapter = MainPagerAdapter(supportFragmentManager).also {
                offscreenPageLimit = it.count
            }
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    uiController.appUI.title.set(
                        binding.vidNavView.menu.getItem(position).title.toString()
                    )
                }
            })
            binding.vidNavView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_nav_movie -> currentItem = 0
                    R.id.action_nav_tv -> currentItem = 1
                    R.id.action_nav_person -> currentItem = 2
                }
                true
            }
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 如果存在启动页广告跳转则进行消费
     */
    private fun consumerSplashADS() {
        intent.getStringExtra(KeyConst.ADS)?.fromJson(
            classOfT = SplashAds::class.java
        )?.let {
            MovieNav.buildMovieDetails(
                it.resId, it.title
            ).navigation()
        }
    }
}