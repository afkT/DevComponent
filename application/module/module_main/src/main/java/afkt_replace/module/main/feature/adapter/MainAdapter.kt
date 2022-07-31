package afkt_replace.module.main.feature.adapter

import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.template.TemplateNav
import afkt_replace.core.lib.router.module.template.TemplateRouter
import afkt_replace.core.lib.router.module.user.UserNav
import afkt_replace.core.lib.router.module.user.UserRouter
import afkt_replace.core.lib.router.module.wan_android.WanAndroidNav
import afkt_replace.core.lib.router.module.wan_android.WanAndroidRouter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.utils.DevFinal

class MainAdapter(
    var themeStyle: ThemeStyle?,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TemplateNav.build(
                    TemplateRouter.PATH_TEMPLATE_FRAGMENT
                ).withObject(DevFinal.STR.STYLE, themeStyle).navigation() as Fragment
            }
            1 -> {
                UserNav.build(
                    UserRouter.PATH_USER_FRAGMENT
                ).withObject(DevFinal.STR.STYLE, themeStyle).navigation() as Fragment
            }
            else -> {
                WanAndroidNav.build(
                    WanAndroidRouter.PATH_ARTICLE_FRAGMENT
                ).withObject(DevFinal.STR.STYLE, themeStyle).navigation() as Fragment
            }
        }
    }
}