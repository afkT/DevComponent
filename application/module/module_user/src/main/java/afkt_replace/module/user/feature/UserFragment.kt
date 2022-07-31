package afkt_replace.module.user.feature

import afkt_replace.core.lib.base.app.BaseFragmentViewBinding
import afkt_replace.core.lib.bean.ThemeStyle
import afkt_replace.core.lib.router.module.user.UserNav
import afkt_replace.core.lib.router.module.user.UserRouter
import afkt_replace.module.user.R
import afkt_replace.module.user.databinding.UserFragmentBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.kotlin.engine.image.display
import dev.kotlin.utils.image.IMAGE_ROUND_10
import dev.kotlin.utils.image.toImageConfig
import dev.kotlin.utils.toSource
import dev.utils.DevFinal

@Route(path = UserRouter.PATH_USER_FRAGMENT, group = UserRouter.GROUP)
class UserFragment : BaseFragmentViewBinding<UserFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.STR.STYLE)
    var themeStyle: ThemeStyle? = null

    override fun baseLayoutId(): Int = R.layout.user_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        themeStyle?.let { uiController.setAllBackground(it.color) }

        binding.vidTitleTv.text = TAG

        UserNav.userProvider()?.apply {
            binding.vidNicknameTv.text = getUserName()
            // 加载用户图片
            binding.vidAvatarIv.display(
                source = "https://picsum.photos/300".toSource(),
                config = IMAGE_ROUND_10.toImageConfig()
            )
        }
    }
}