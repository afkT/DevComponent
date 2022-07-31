package afkt_replace.core.lib.router.module.user

import afkt_replace.core.lib.router.BaseProvider

/**
 * detail: User 各个组件通讯接口
 * @author Ttt
 */
interface IUserProvider : BaseProvider {

    /**
     * 获取用户名
     * @return 用户名
     */
    fun getUserName(): String?
}