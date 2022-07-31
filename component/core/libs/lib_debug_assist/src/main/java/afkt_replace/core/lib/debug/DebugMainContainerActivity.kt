package afkt_replace.core.lib.debug

import afkt_replace.core.lib.debug.databinding.CoreDebugAssistMainActivityBinding
import android.app.Activity
import android.os.Bundle
import dev.DevHttpCaptureCompiler
import dev.capture.UtilsPublic
import dev.capture.compiler.R
import dev.environment.DevEnvironment
import dev.environment.DevEnvironmentActivity
import dev.utils.app.BarUtils
import dev.utils.app.ResourceUtils
import dev.utils.app.ScreenUtils
import dev.utils.app.toast.ToastTintUtils
import me.ele.uetool.UETool

/**
 * detail: Debug 入口容器 Activity
 * @author Ttt
 */
class DebugMainContainerActivity : Activity() {

    val binding: CoreDebugAssistMainActivityBinding by lazy {
        CoreDebugAssistMainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // =============
        // = StatusBar =
        // =============

        // 添加边距
        BarUtils.addMarginTopEqualStatusBarHeight(
            binding.vidStatusFrame
        )
        // 设置状态栏颜色
        BarUtils.setStatusBarColor(
            this, ResourceUtils.getColor(R.color.dev_http_capture_title_bg_color)
        )

        // ============
        // = Listener =
        // ============

        // 初始化事件
        initListener()

        // =========
        // = Button =
        // =========

        // 返回键
        binding.vidBackIv.setOnClickListener {
            finish()
        }
        // 切换环境
        binding.vidEnvironmentTv.setOnClickListener {
            DevEnvironmentActivity.start(this)
        }
        // 抓包数据
        binding.vidHttpCaptureTv.setOnClickListener {
            DevHttpCaptureCompiler.start(this)
        }
        // 删除抓包数据
        binding.vidDeleteHttpCaptureTv.setOnClickListener {
            ToastTintUtils.info("开始删除, 请等待删除结果...")
            Thread {
                UtilsPublic.deleteAllModule()
                ToastTintUtils.success("删除成功")
            }.start()
        }
        // 打开 UI 可视化调试
        binding.vidOpenUeToolTv.setOnClickListener {
            try {
                UETool.showUETMenu((ScreenUtils.getHeightDpi() * 0.4).toInt())
            } catch (e: Exception) {
            }
        }
        // 关闭 UI 可视化调试
        binding.vidCloseUeToolTv.setOnClickListener {
            try {
                UETool.dismissUETMenu()
            } catch (e: Exception) {
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (mChangeEnvironment) {
            ToastTintUtils.error("已切换服务器地址, 需关闭并清空 APP 后台重新打开.")
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    // 判断是否切换过环境
    private var mChangeEnvironment = false

    /**
     * 初始化事件
     */
    private fun initListener() {
        // 环境改变通知
        DevEnvironment.addOnEnvironmentChangeListener { module, oldEnvironment, newEnvironment ->
            StringBuilder().apply {
                append("module")
                append("\nname: ").append(module.name)
                append("\nalias: ").append(module.alias)
                append("\n\n")
                append("历史环境")
                append("\nname: ").append(oldEnvironment.name)
                append("\nvalue: ").append(oldEnvironment.value)
                append("\nalias: ").append(oldEnvironment.alias)
                append("\n\n")
                append("当前环境 ( 已切换 )")
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