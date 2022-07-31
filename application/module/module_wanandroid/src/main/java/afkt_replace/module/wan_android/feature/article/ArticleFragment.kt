package afkt_replace.module.wan_android.feature.article

import afkt_replace.core.lib.base.app.BaseFragment
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.wan_android.WanAndroidRouter
import afkt_replace.module.wan_android.R
import afkt_replace.module.wan_android.WanAndroidViewModel
import afkt_replace.module.wan_android.databinding.WanandroidFragmentArticleBinding
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.DevFinal

@Route(path = WanAndroidRouter.PATH_ARTICLE_FRAGMENT, group = WanAndroidRouter.GROUP)
class ArticleFragment : BaseFragment<WanandroidFragmentArticleBinding, WanAndroidViewModel>() {

    @JvmField
    @Autowired(name = DevFinal.STR.STYLE)
    var themeStyle: ThemeStyle? = null

    override fun baseLayoutId(): Int = R.layout.wanandroid_fragment_article

    override fun preLoad() {
        super.preLoad()
        themeStyle?.let { uiController.setAllBackground(it.color) }

        // 禁用刷新、加载功能
        binding.vidRefresh.setEnableRefresh(false)
            .setEnableLoadMore(false)
        // 绑定适配器
        mAdapter.bindAdapter(
            binding.vidRefresh.getRecyclerView()
        )
    }

    override fun initObserve() {
        super.initObserve()

        viewModel.articleList.observe(this) {
            it.data?.datas?.let { list ->
                mAdapter.setDataList(list)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // 请求文章列表
        viewModel.requestArticleLists()
    }

    // ==========
    // = 内部处理 =
    // ==========

    // 文章 Adapter
    val mAdapter = ArticleAdapter()
}