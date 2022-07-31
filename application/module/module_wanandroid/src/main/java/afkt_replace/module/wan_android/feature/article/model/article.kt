package afkt_replace.module.wan_android.feature.article.model

///**
// * detail: 文章信息实体类
// * @author Ttt
// */
//class ArticleBean {
//
//    var data: DataBean? = null
//
//    class DataBean {
//
//        var datas: List<ListBean>? = null
//
//        class ListBean {
//            var id = 0
//            var link: String? = null
//            var niceDate: String? = null
//            var niceShareDate: String? = null
//            var author: String? = null
//            var title: String? = null
//            var type = 0
//        }
//    }
//}

data class ArticleBean(
    val data: DataBean?
)

data class DataBean(
    val datas: List<ListBean>?
)

data class ListBean(
    val id: Int,
    val link: String?,
    val niceDate: String?,
    val niceShareDate: String?,
    val author: String?,
    val title: String?,
    val type: Int,
)