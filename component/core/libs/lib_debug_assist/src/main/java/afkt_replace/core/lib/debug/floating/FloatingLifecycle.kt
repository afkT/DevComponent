package afkt_replace.core.lib.debug.floating

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dev.utils.app.assist.floating.IFloatingActivity

/**
 * detail: 悬浮窗生命周期
 * @author Ttt
 */
internal class FloatingLifecycle(private val activity: FragmentActivity) : DefaultLifecycleObserver,
    IFloatingActivity {

    init {
        activity.lifecycle.addObserver(this)
    }

    // =====================
    // = IFloatingActivity =
    // =====================

    override fun getAttachActivity(): Activity {
        return activity
    }

    override fun getMapFloatingKey(): String {
        return this.toString()
    }

    override fun getMapFloatingView(): View {
        return FloatingUtils.instance.createFloatingView(this)
    }

    override fun getMapFloatingViewLayoutParams(): ViewGroup.LayoutParams {
        return FloatingUtils.instance.createLayoutParams(this)
    }

    // ============================
    // = DefaultLifecycleObserver =
    // ============================

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        // 添加悬浮窗 View
        FloatingUtils.instance.addFloatingView(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        // 移除悬浮窗 View
        FloatingUtils.instance.removeFloatingView(this)
    }
}