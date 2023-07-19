package afkt_replace.core.lib.ui.widget.view_assist.loading_skeleton

import afkt_replace.core.lib.ui.R
import afkt_replace.core.lib.ui.databinding.CoreUiViewAssistPageSkeletonFailedBinding
import afkt_replace.core.lib.ui.databinding.CoreUiViewAssistPageSkeletonLoadingBinding
import afkt_replace.core.lib.ui.widget.view_assist.IViewAssistFactory
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import dev.utils.app.ViewUtils
import dev.widget.assist.ViewAssist

/**
 * detail: Page Loading 骨架 View 辅助类
 * @author Ttt
 */
class PageLoadingSkeletonViewAssist(
    private val viewAssist: ViewAssist?,
    private val contentLayout: View?,
    private val reload: View.OnClickListener
) : IViewAssistFactory {

    // ======================
    // = IViewAssistFactory =
    // ======================

    // ==========
    // = 变量获取 =
    // ==========

    override fun viewAssist(): ViewAssist? {
        return viewAssist
    }

    override fun reloadListener(): View.OnClickListener {
        return reload
    }

    override fun contentLayout(): View? {
        return contentLayout
    }

    // ==============
    // = 对外公开方法 =
    // ==============

    override fun register() {
        viewAssist?.let {
            // 设置加载中样式
            it.registerIng(ingAdapter)
            // 设置加载成功样式
            it.registerSuccess(successAdapter)
            // 设置加载失败样式
            it.registerFailed(FailedAdapter(reload))
            // 监听切换事件
            it.setListener(object : ViewAssist.Listener {
                override fun onRemove(
                    assist: ViewAssist?,
                    type: Int,
                    removeView: Boolean
                ) {
                }

                override fun onNotFound(
                    assist: ViewAssist?,
                    type: Int
                ) {
                }

                override fun onChange(
                    assist: ViewAssist?,
                    type: Int,
                    oldType: Int
                ) {
                    ViewUtils.setVisibility(
                        type == ViewAssist.TYPE_SUCCESS,
                        contentLayout
                    )
                }
            })
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    // 设置加载中样式
    private val ingAdapter = object : ViewAssist.Adapter {
        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            val binding = DataBindingUtil.inflate<CoreUiViewAssistPageSkeletonLoadingBinding>(
                inflater, R.layout.core_ui_view_assist_page_skeleton_loading,
                null, false
            )
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
        val reload: View.OnClickListener
    ) : ViewAssist.Adapter {

        lateinit var binding: CoreUiViewAssistPageSkeletonFailedBinding

        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            binding = DataBindingUtil.inflate(
                inflater, R.layout.core_ui_view_assist_page_skeleton_failed,
                null, false
            )
            return binding.root
        }

        override fun onBindView(
            assist: ViewAssist,
            view: View,
            type: Int
        ) {
            binding.vidRetryCl.setOnClickListener(reload)
        }
    }
}