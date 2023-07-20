package afkt_replace.core.ui.widget.view_assist.loading_skeleton

import afkt_replace.core.ui.widget.view_assist.IViewAssistFactory
import afkt_replace.core.ui.widget.view_assist.IViewAssistFactoryRegister

/**
 * detail: Page Loading 骨架工厂类
 * @author Ttt
 */
class PageLoadingSkeletonFactory : IViewAssistFactoryRegister {

    override fun register(factory: IViewAssistFactory) {
        factory.register()
    }
}