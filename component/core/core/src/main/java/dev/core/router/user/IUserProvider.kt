package dev.core.router.user

import dev.core.lib.bean.user.UserInfo
import dev.core.router.BaseProvider

/**
 * detail: User 各个组件通讯接口
 * @author Ttt
 */
interface IUserProvider : BaseProvider {

    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean

    /**
     * 获取用户信息
     * @return [UserInfo]
     */
    fun getUserInfo(): UserInfo?
}