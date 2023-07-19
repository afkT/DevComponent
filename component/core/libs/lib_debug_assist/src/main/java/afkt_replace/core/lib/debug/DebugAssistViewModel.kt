package afkt_replace.core.lib.debug

import afkt_replace.core.lib.base.app.BaseViewModel
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import dev.DevHttpCapture
import dev.DevHttpCaptureCompiler
import dev.environment.DevEnvironment
import dev.environment.DevEnvironmentActivity
import dev.mvvm.command.BindingConsumer
import dev.mvvm.utils.toResString
import dev.utils.app.ScreenUtils
import dev.utils.app.toast.ToastTintUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ele.uetool.UETool

class DebugAssistViewModel : BaseViewModel() {

    // 切换环境
    val onClickEnvironment = object : BindingConsumer<View> {
        override fun accept(value: View) {
            DevEnvironmentActivity.start(value.context)
        }
    }

    // 抓包数据
    val onClickHttpCapture = object : BindingConsumer<View> {
        override fun accept(value: View) {
            DevHttpCaptureCompiler.start(value.context)
        }
    }

    // 删除抓包数据
    val onClickDeleteHttpCapture = object : BindingConsumer<View> {
        override fun accept(value: View) {
            ToastTintUtils.info(R.string.str_start_deleting_wait_result.toResString())
            viewModelScope.launch(Dispatchers.IO) {
                DevHttpCapture.utils().deleteAllModule()
                ToastTintUtils.success(R.string.str_deleted_successfully.toResString())
            }
        }
    }

    // 打开 UI 可视化调试
    val onClickOpenUeTool = object : BindingConsumer<View> {
        override fun accept(value: View) {
            try {
                UETool.showUETMenu((ScreenUtils.getHeightDpi() * 0.4).toInt())
            } catch (_: Exception) {
            }
        }
    }

    // 关闭 UI 可视化调试
    val onClickCloseUeTool = object : BindingConsumer<View> {
        override fun accept(value: View) {
            try {
                UETool.dismissUETMenu()
            } catch (_: Exception) {
            }
        }
    }

    // ==========
    // = 生命周期 =
    // ==========

    // 判断是否切换过环境
    private var mChangeEnvironment = false

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        if (mChangeEnvironment) {
            ToastTintUtils.error(R.string.str_switched_environments.toResString())
        }
    }

    // =

    init {
        // 环境改变通知
        DevEnvironment.addOnEnvironmentChangeListener { module, oldEnvironment, newEnvironment ->
            StringBuilder().apply {
                append("module")
                append("\nname: ").append(module.name)
                append("\nalias: ").append(module.alias)
                append("\n\n")
                append("historical environment")
                append("\nname: ").append(oldEnvironment.name)
                append("\nvalue: ").append(oldEnvironment.value)
                append("\nalias: ").append(oldEnvironment.alias)
                append("\n\n")
                append("current environment ( switched )")
                append("\nname: ").append(newEnvironment.name)
                append("\nvalue: ").append(newEnvironment.value)
                append("\nalias: ").append(newEnvironment.alias)

                val content = toString()
                ToastTintUtils.normal(content)
                // 表示切换过
                mChangeEnvironment = true
            }
        }
    }
}