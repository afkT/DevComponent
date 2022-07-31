package afkt_replace.module.user

import afkt_replace.core.lib.router.BaseProviderExt
import afkt_replace.core.lib.router.module.user.IUserProvider
import afkt_replace.core.lib.router.module.user.UserRouter
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import dev.utils.common.RandomUtils

@Route(path = UserRouter.PATH_USER_PROVIDER, group = UserRouter.GROUP)
class UserProvider : IUserProvider,
    BaseProviderExt(UserProvider::class.java.simpleName) {

    override fun init(context: Context?) {
    }

    // =================
    // = IUserProvider =
    // =================

    override fun getUserName(): String {
        return RandomUtils.getRandomLetters(7)
    }
}