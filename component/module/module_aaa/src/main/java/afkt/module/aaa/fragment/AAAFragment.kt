package afkt.module.aaa.fragment

import afkt.module.aaa.R
import afkt.module.aaa.databinding.AaaFragmentBinding
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import dev.core.lib.base.app.BaseFragmentViewBinding
import dev.core.lib.bean.AfkT
import dev.core.router.aaa.AAARouter
import dev.utils.DevFinal

@Route(path = AAARouter.PATH_AAA_FRAGMENT, group = AAARouter.GROUP)
class AAAFragment : BaseFragmentViewBinding<AaaFragmentBinding>() {

    @JvmField
    @Autowired(name = DevFinal.DATA)
    var afkt: AfkT? = null

    override fun baseLayoutId(): Int = R.layout.aaa_fragment

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        binding.vidAfContentTv.text = "$TAG \r\n ${afkt?.value}"

        afkt?.let { uiController.setAllBackground(it.color) }
    }
}