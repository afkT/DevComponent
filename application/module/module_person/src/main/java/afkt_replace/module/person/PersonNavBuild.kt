package afkt_replace.module.person

import afkt_replace.core.project.router.module.person.PersonNav

/**
 * detail: PersonNav 跳转参数构建
 * @author Ttt
 */
object PersonNavBuild {

    /**
     * 跳转 Person Details Path
     * @receiver TMDBPerson
     * @return Postcard
     */
    fun afkt_replace.core.project.bean.person.TMDBPerson.routerPersonDetails() {
        PersonNav.buildPersonDetails(
            id.toString(), name
        ).navigation()
    }
}