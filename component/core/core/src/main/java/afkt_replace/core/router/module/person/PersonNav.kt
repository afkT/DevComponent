package afkt_replace.core.router.module.person

import afkt_replace.core.config.ParamConst
import com.alibaba.android.arouter.facade.Postcard

/**
 * detail: 具体 navigation 类
 * @author Ttt
 * 不封装在对应的 Router 类中, 统一通过 Nav 类进行跳转、获取
 * 方便后续二次封装、统一编辑等处理
 */
object PersonNav {

    // ==========
    // = 快捷方法 =
    // ==========

    /**
     * 内部传入 GROUP 尽量各个模块直接通过对应 [build] 方法跳转
     * 便于代码跳转直观、对外避免跳转错 GROUP ( Module )
     */
    fun build(path: String): Postcard {
        return PersonRouter.build(path)
    }

    // ==========
    // = 跳转方法 =
    // ==========

    /**
     * 模块入口路由跳转
     */
    fun routerMain() {
        build(PersonRouter.PATH_MAIN).navigation()
    }

    // ==================
    // = Person Details =
    // ==================

    /**
     * 构建 Person Details Path Router
     * @param personId person id
     * @param name person name
     * @return Postcard
     */
    fun buildPersonDetails(
        personId: String,
        name: String?
    ): Postcard {
        return build(PersonRouter.PATH_PERSON_DETAILS_ACTIVITY).apply {
            withString(ParamConst.PERSON_ID, personId)
            withString(ParamConst.NAME, name)
        }
    }
}