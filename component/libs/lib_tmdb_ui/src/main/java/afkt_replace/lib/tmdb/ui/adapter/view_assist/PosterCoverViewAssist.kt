package afkt_replace.lib.tmdb.ui.adapter.view_assist

import afkt_replace.core.lib.ui.R
import afkt_replace.core.lib.ui.databinding.CoreUiViewAssistRecyFailedBinding
import afkt_replace.core.lib.ui.databinding.CoreUiViewAssistRecyLoadingBinding
import afkt_replace.core.lib.ui.skin.AppThemeRes
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import dev.utils.common.able.Getable
import dev.widget.assist.ViewAssist

class PosterCoverViewAssist(
    val appThemeRes: Getable.Get<AppThemeRes>
) {

    /**
     * 注册 Recycler Loading type
     * @param viewAssist [ViewAssist]
     * @param reload 重新加载事件
     */
    fun registerRecyclerLoading(
        viewAssist: ViewAssist?,
        reload: View.OnClickListener
    ) {
        viewAssist?.let {
            // 设置加载中样式
            it.register(ViewAssist.TYPE_ING, ingAdapter)
            // 设置加载成功样式
            it.register(ViewAssist.TYPE_SUCCESS, successAdapter)
            // 设置加载失败样式
            it.register(ViewAssist.TYPE_FAILED, FailedAdapter(appThemeRes, reload))
        }
    }

    // 设置加载中样式
    private val ingAdapter = object : ViewAssist.Adapter {
        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            val binding = DataBindingUtil.inflate<CoreUiViewAssistRecyLoadingBinding>(
                inflater, R.layout.core_ui_view_assist_recy_loading,
                null, false
            )
            appThemeRes.get()?.let {
                binding.appThemeRes = it
            }
            return binding.root
        }

        override fun onBindView(
            assist: ViewAssist,
            view: View,
            type: Int
        ) {
        }
    }

    // 设置加载成功样式
    private val successAdapter = object : ViewAssist.Adapter {
        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            return View(inflater.context)
        }

        override fun onBindView(
            assist: ViewAssist,
            view: View,
            type: Int
        ) {
            // 可以设置渐变动画, 并在结束时隐藏根布局 -> assist.goneWrapper()
            assist.goneWrapper()
        }
    }

    // 设置加载失败样式
    private class FailedAdapter(
        val appThemeRes: Getable.Get<AppThemeRes>,
        val reload: View.OnClickListener
    ) : ViewAssist.Adapter {
        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            val binding = DataBindingUtil.inflate<CoreUiViewAssistRecyFailedBinding>(
                inflater, R.layout.core_ui_view_assist_recy_failed,
                null, false
            )
            appThemeRes.get()?.let {
                binding.appThemeRes = it
            }
            return binding.root
        }

        override fun onBindView(
            assist: ViewAssist,
            view: View,
            type: Int
        ) {
            view.setOnClickListener(reload)
        }
    }
}