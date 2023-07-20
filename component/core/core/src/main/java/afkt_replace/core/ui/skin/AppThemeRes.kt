package afkt_replace.core.ui.skin

import afkt_replace.core.ui.skin.InitMerge.mergeInitAppThemeRes
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.mvvm.base.attribute.Margins

/**
 * detail: APP 主题样式资源类
 * @author Ttt
 * 整个 APP 主题、换肤相关控制
 * 换肤功能要求:
 * 1.支持全局同步换肤 ( 无需重启应用 )
 * 2.可自行选择是否全局换肤 ( 且默认赋值全局资源 )
 * 3.页面可以直接通过更新 LiveData 资源值更新 UI
 * 4.支持部分资源不全局换肤
 * 实现:
 * 为什么需要传入 [LifecycleOwner]
 * 是因为想要通过更新 LiveData 同步更新 UI 又不影响全局资源值
 * 只能通过监听全局资源 LiveData 来达到全局同步换肤
 * 注意事项:
 * 必须在 init 方法中调用对应的 commonObserve 进行绑定监听以及初始化
 */
class AppThemeRes(
    // 用于每个页面生命周期监听
    private val owner: LifecycleOwner?,
    // 是否全局同步换肤
    val isSyncSkin: Boolean,
    // 忽略不进行全局换肤资源
    val ignoreSkinList: List<String> = arrayListOf()
) {

    // 主题背景色
    val themeBackground = MutableLiveData<Drawable>()

    // =========
    // = 状态栏 =
    // =========

    // 状态栏高度
    val statusBarHeight = MutableLiveData<Int>()

    // 状态栏背景色
    val statusBarBackground = MutableLiveData<Drawable>()

    // =========
    // = 标题栏 =
    // =========

    // 标题栏高度
    val titleBarHeight = MutableLiveData<Int>()

    // 标题栏背景色
    val titleBarBackground = MutableLiveData<Drawable>()

    // =======
    // = 标题 =
    // =======

    // 标题字体颜色
    val titleTextColor = MutableLiveData<ColorStateList>()

    // 标题字体大小
    val titleTextSize = MutableLiveData<Int>()

    // =================
    // = 标题栏 - 返回键 =
    // =================

    // 标题返回键图标
    val titleBackIcon = MutableLiveData<Drawable>()

    // 标题返回键图标边距
    val titleBackMargin = MutableLiveData<Margins>()

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 通用监听方法
     * @param key 待校验 Key
     * @param globalRes 全局资源 AppTheme.xxx
     * @param currentRes 当前类资源值
     */
    internal fun <T> commonObserve(
        key: String,
        globalRes: LiveData<T>,
        currentRes: MutableLiveData<T>
    ) {
        // 需要同步换肤才处理
        if (isSyncSkin && owner != null) {
            if (!ignoreSkinList.contains(key)) {
                globalRes.observe(owner) {
                    currentRes.postValue(it)
                }
            }
        }
        currentRes.value = globalRes.value
    }

    init {
        mergeInitAppThemeRes()
    }

    /**
     * 创建方法
     * @param isSyncSkin 是否全局同步换肤
     * @param ignoreSkinList 忽略不进行全局换肤资源
     * @return [AppThemeRes]
     */
    fun create(
        isSyncSkin: Boolean,
        ignoreSkinList: List<String> = arrayListOf()
    ): AppThemeRes {
        return AppThemeRes(owner, isSyncSkin, ignoreSkinList)
    }

    // ==========
    // = 克隆方法 =
    // ==========

    /**
     * 克隆方法
     * @param ignoreSkinList 忽略不进行全局换肤资源
     * @return [AppThemeRes]
     */
    fun createByClone(ignoreSkinList: List<String> = arrayListOf()): AppThemeRes {
        return create(
            isSyncSkin, this.ignoreSkinList + ignoreSkinList
        ).also { newRes ->

            // 主题背景色
            newRes.themeBackground.value = this.themeBackground.value

            // =========
            // = 状态栏 =
            // =========

            // 状态栏高度
            newRes.statusBarHeight.value = this.statusBarHeight.value

            // 状态栏背景色
            newRes.statusBarBackground.value = this.statusBarBackground.value

            // =========
            // = 标题栏 =
            // =========

            // 标题栏高度
            newRes.titleBarHeight.value = this.titleBarHeight.value

            // 标题栏背景色
            newRes.titleBarBackground.value = this.titleBarBackground.value

            // =======
            // = 标题 =
            // =======

            // 标题字体颜色
            newRes.titleTextColor.value = this.titleTextColor.value

            // 标题字体大小
            newRes.titleTextSize.value = this.titleTextSize.value

            // =================
            // = 标题栏 - 返回键 =
            // =================

            // 标题返回键图标
            newRes.titleBackIcon.value = this.titleBackIcon.value

            // 标题返回键图标边距
            newRes.titleBackMargin.value = this.titleBackMargin.value
        }
    }
}