package afkt.module.commodity.fragment

import afkt.module.commodity.R
import afkt.module.commodity.adapter.ShopCartAdapter
import afkt.module.commodity.databinding.CommodityFragmentShopCartBinding
import afkt.project.ui.widget.decoration.LastLineItemDecoration
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import dev.core.lib.base.app.BaseFragmentViewBinding
import dev.core.lib.bean.AfkT
import dev.core.lib.bean.commodity.CommodityBean
import dev.core.lib.ui.widget.BaseTextView
import dev.core.router.commodity.CommodityRouter
import dev.utils.DevFinal
import dev.utils.app.ResourceUtils
import dev.utils.app.ViewUtils
import dev.utils.app.helper.QuickHelper
import dev.utils.app.toast.ToastTintUtils
import dev.utils.common.ChineseUtils
import dev.utils.common.RandomUtils
import kotlinx.coroutines.*

@Route(path = CommodityRouter.PATH_SHOP_CART_FRAGMENT, group = CommodityRouter.GROUP)
class ShopCartFragment : BaseFragmentViewBinding<CommodityFragmentShopCartBinding>() {

    @JvmField
    @Autowired(name = DevFinal.DATA)
    var afkt: AfkT? = null

    // 购物车 Adapter
    var mAdapter = ShopCartAdapter()

    override fun baseLayoutId(): Int = R.layout.commodity_fragment_shop_cart

    override fun preLoad() {
        super.preLoad()
        afkt?.let { uiController.setAllBackground(it.color) }
        addEditButton()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // 设置数据源
        mAdapter.setDataList(newList(15), false)
        // 添加分割线
        binding.vidCfscRefresh.getRecyclerView()?.addItemDecoration(
            LastLineItemDecoration(ResourceUtils.getDimension(R.dimen.un_dp_10), Color.TRANSPARENT)
        )
        // 绑定适配器、设置加载事件
        binding.vidCfscRefresh.setAdapter(mAdapter)
            .setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                override fun onRefresh(refreshLayout: RefreshLayout) {
                    GlobalScope.launch {
                        delay(1000L)
                        withContext(Dispatchers.Main) {
                            mAdapter.page.setPage(1).isLastPage = false
                            // 结束刷新状态
                            binding.vidCfscRefresh.finishRefresh()
                            // 重置数据源
                            mAdapter.setDataList(newList(15))
                        }
                    }
                }

                override fun onLoadMore(refreshLayout: RefreshLayout) {
                    if (mAdapter.page.isLastPage) return

                    GlobalScope.launch {
                        delay(1000L)
                        withContext(Dispatchers.Main) {
                            mAdapter.page.apply {
                                var number = nextPage().page
                                isLastPage = (number >= 5)
                            }
                            // 结束加载状态
                            binding.vidCfscRefresh.finishLoadMore()
                                .setNoMoreData(mAdapter.page.isLastPage)
                            // 累加数据源
                            mAdapter.addDatas(newList(RandomUtils.getRandom(5, 10)))
                        }
                    }
                }
            })
    }

    private fun newList(count: Int): MutableList<CommodityBean> {
        var lists = mutableListOf<CommodityBean>()
        for (i in 1..count) {
            lists.add(
                CommodityBean(
                    ChineseUtils.randomWord(RandomUtils.getRandom(5, 40)),
                    "https://picsum.photos/20${RandomUtils.getRandom(0, 10)}",
                    RandomUtils.nextDoubleRange(15.159, 79.356),
                    RandomUtils.getRandom(100, 120)
                )
            )
        }
        return lists
    }

    // ==========
    // = 按钮处理 =
    // ==========

    // 编辑按钮
    var editView: BaseTextView? = null

    // 取消编辑按钮
    var cancelView: BaseTextView? = null

    // 确定按钮
    var confirmView: BaseTextView? = null

    // 全选按钮
    var allSelectView: BaseTextView? = null

    // 非全选按钮
    var unAllSelectView: BaseTextView? = null

    // 反选按钮
    var inverseSelectView: BaseTextView? = null

    private fun addEditButton() {
        context?.let { context ->
            var linear = LinearLayout(context)
            linear.orientation = LinearLayout.HORIZONTAL
            linear.gravity = Gravity.RIGHT

            linear.addView(createTextView("编辑") {
                mAdapter.setEditState(true)
                // 显示其他按钮、隐藏编辑按钮
                ViewUtils.toggleVisibilitys(
                    arrayOf<View?>(
                        cancelView,
                        confirmView,
                        allSelectView,
                        unAllSelectView,
                        inverseSelectView
                    ), editView
                )
            }.also { editView = it })

            linear.addView(createTextView("取消") {
                mAdapter.setEditState(false).clearSelectAll()
                // 显示编辑按钮、隐藏其他按钮
                ViewUtils.toggleVisibilitys(
                    editView,
                    cancelView,
                    confirmView,
                    allSelectView,
                    unAllSelectView,
                    inverseSelectView
                )
            }.also { cancelView = it })

            linear.addView(createTextView("确定") {
                val builder = StringBuilder()
                builder.append("是否全选: ").append(mAdapter.isSelectAll())
                builder.append("\n是否选中: ").append(mAdapter.isSelect())
                builder.append("\n选中数量: ").append(mAdapter.getSelectSize())
                builder.append("\n总数: ").append(mAdapter.getDataCount())
                ToastTintUtils.normal(builder.toString())
            }.also { confirmView = it })

            linear.addView(createTextView(
                "全选"
            ) { mAdapter.selectAll() }.also { allSelectView = it })

            linear.addView(createTextView(
                "非全选"
            ) { mAdapter.clearSelectAll() }.also { unAllSelectView = it })

            linear.addView(createTextView(
                "反选"
            ) { mAdapter.inverseSelect() }.also { inverseSelectView = it })

            // 显示编辑按钮
            ViewUtils.setVisibility(true, editView)

            contentAssist.addContentView(linear, 0)
        }
    }

    /**
     * 创建 TextView
     * @param text            文案
     * @param onClickListener 点击事件
     * @return [BaseTextView]
     */
    private fun createTextView(
        text: String,
        onClickListener: View.OnClickListener
    ): BaseTextView? {
        return context?.let {
            QuickHelper.get(BaseTextView(it))
                .setVisibility(false) // 默认隐藏
                .setText(text)
                .setBold()
                .setTextColor(ResourceUtils.getColor(R.color.white))
                .setTextSizeBySp(13.0f)
                .setPadding(ResourceUtils.getDimensionInt(R.dimen.un_dp_10))
                .setOnClicks(onClickListener)
                .getView()
        }
    }
}