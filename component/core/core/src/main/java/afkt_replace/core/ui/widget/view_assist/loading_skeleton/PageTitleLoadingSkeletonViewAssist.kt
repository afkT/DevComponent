package afkt_replace.core.ui.widget.view_assist.loading_skeleton

import afkt_replace.core.R
import afkt_replace.core.config.AppLibConfig
import afkt_replace.core.databinding.CoreUiBaseStatusBarBinding
import afkt_replace.core.databinding.CoreUiBaseTitleBarBinding
import afkt_replace.core.databinding.CoreUiViewAssistPageTitleSkeletonFailedBinding
import afkt_replace.core.databinding.CoreUiViewAssistPageTitleSkeletonLoadingBinding
import afkt_replace.core.ui.widget.view_assist.IViewAssistFactory
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dev.utils.app.ViewUtils
import dev.utils.common.able.Bindingable
import dev.widget.assist.ViewAssist

interface PageTitleBindable : Bindingable.BindingByParam3<
        Boolean, ViewDataBinding,
        CoreUiBaseStatusBarBinding, CoreUiBaseTitleBarBinding>

/**
 * detail: Page Title Loading 骨架 View 辅助类
 * @author Ttt
 * 包含 statusBar、titleBar
 */
class PageTitleLoadingSkeletonViewAssist(
    private val viewAssist: ViewAssist?,
    private val contentLayout: View?,
    private val reload: View.OnClickListener,
    // 解决无法传入 BaseUIController 操作
    private val bindable: PageTitleBindable
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
            it.registerIng(IngAdapter(bindable))
//            // 设置加载成功样式
//            it.registerSuccess(successAdapter)
            // 设置加载失败样式
            it.registerFailed(FailedAdapter(reload, bindable))
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
                    /**
                     * 通过不绑定 registerSuccess(successAdapter) 然后调用 showSuccess(false)
                     * 内部触发 onNotFound 并不会移除 viewAssist 其他 Type View ( 如当前属于 Ing Type)
                     * 来实现从 Ing Type 过度到成功显示 contentLayout 布局的动画
                     */
                    if (type == ViewAssist.TYPE_SUCCESS) {
                        // 设置加载中到成功的过度动画
                        ViewUtils.setVisibility(true, contentLayout)
                        // 隐藏占位布局
                        assist?.goneWrapper(
                            AppLibConfig.ANIM_VIEW_ASSIST_DURATION_MILLIS,
                            false, null
                        )
                    }
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
    private class IngAdapter(
        val bindable: PageTitleBindable
    ) : ViewAssist.Adapter {
        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            val binding = DataBindingUtil.inflate<CoreUiViewAssistPageTitleSkeletonLoadingBinding>(
                inflater, R.layout.core_ui_view_assist_page_title_skeleton_loading,
                null, false
            )
            bindable.bind(binding, binding.vidHeader.vidStatusBar, binding.vidHeader.vidTitleBar)
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
        val reload: View.OnClickListener,
        val bindable: PageTitleBindable
    ) : ViewAssist.Adapter {

        lateinit var binding: CoreUiViewAssistPageTitleSkeletonFailedBinding

        override fun onCreateView(
            assist: ViewAssist,
            inflater: LayoutInflater
        ): View {
            binding = DataBindingUtil.inflate(
                inflater, R.layout.core_ui_view_assist_page_title_skeleton_failed,
                null, false
            )
            bindable.bind(binding, binding.vidHeader.vidStatusBar, binding.vidHeader.vidTitleBar)
            return binding.root
        }

        override fun onBindView(
            assist: ViewAssist,
            view: View,
            type: Int
        ) {
            binding.vidIcon.setOnClickListener(reload)
        }
    }
}