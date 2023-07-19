package afkt_replace.core.lib.base.controller

import afkt_replace.core.lib.base.app.BaseViewModel
import afkt_replace.core.lib.base.controller.inter.IController
import afkt_replace.core.lib.base.controller.viewmodel.IntentDataViewModel
import androidx.databinding.ViewDataBinding

/**
 * detail: 基础 ViewModel 控制封装
 * @author Ttt
 */
class BaseVMController<VDB : ViewDataBinding, VM : BaseViewModel>(
    // Base 汇总控制器接口
    private val controller: IController
) {

    /**
     * 初始化 ViewModel 属性值
     * @param binding VDB
     * @param viewModel VM
     * @param uiController 基础 UI 控制封装
     * @param dataController 基础 Data 控制封装
     */
    internal fun initialize(
        binding: VDB,
        viewModel: VM,
        uiController: BaseUIController,
        dataController: BaseDataController,
        keyEventController: BaseKeyEventController?
    ) {
        // 内部 VDB 定义变量初始化
        innerVDBDefineVariable(binding, uiController)

        if (viewModel is IntentDataViewModel) {
            viewModel.intentData.set(dataController.intentData)
        }

        // ===============
        // = IController =
        // ===============

        // 是否初始化 ControllerViewModel 相关参数
        if (controller.isControllerViewModelInit()) {
//            // 基础 UI 控制封装
//            viewModel.uiController.postValue(uiController)
//            // 基础 Data 控制封装
//            viewModel.dataController.postValue(dataController)
//            // 基础 KeyEvent 物理按键 控制封装
//            viewModel.keyEventController.postValue(keyEventController)
            // 基础 UI 控制封装
            viewModel.uiController.value = uiController
            // 基础 Data 控制封装
            viewModel.dataController.value = dataController
            // 基础 KeyEvent 物理按键 控制封装
            viewModel.keyEventController.value = keyEventController
        }
    }

    // ==========
    // = 内部方法 =
    // ==========

    /**
     * 内部 VDB 定义变量初始化
     * @param binding VDB
     * @param uiController 基础 UI 控制封装
     */
    private fun innerVDBDefineVariable(
        binding: VDB,
        uiController: BaseUIController
    ) {
        uiController.initializeVDBVariable(binding)
    }
}