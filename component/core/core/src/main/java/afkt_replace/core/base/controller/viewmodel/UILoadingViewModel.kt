package afkt_replace.core.base.controller.viewmodel

/**
 * detail: UI Loading ViewModel
 * @author Ttt
 * 数据请求、加载等通用 Loading 控制
 * 注意如果 Activity、Fragment 创建同一个 ViewModel 会导致对象复用、数据覆盖
 * 需自行根据场景设计使用
 */
open class UILoadingViewModel : ControllerViewModel() {

    // Dialog or ViewAssist ...
//    val loading = MutableLiveData<ViewAssist>()
//    val loading = MutableLiveData<Dialog>()
}