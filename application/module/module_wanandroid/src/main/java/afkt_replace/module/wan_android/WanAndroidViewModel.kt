package afkt_replace.module.wan_android

import afkt_replace.module.wan_android.data.api.WanAndroidAPI
import afkt_replace.module.wan_android.feature.article.model.ArticleBean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WanAndroidViewModel : ViewModel() {

    // 文章列表数据源
    private val _articleList = MutableLiveData<ArticleBean>()
    val articleList: LiveData<ArticleBean>
        get() = _articleList

    // ==========
    // = 请求方法 =
    // ==========

    /**
     * 请求文章列表
     */
    fun requestArticleLists() {
        viewModelScope.launch {
            _articleList.postValue(
                WanAndroidAPI.api().getArticleList(1)
            )
        }
    }
}