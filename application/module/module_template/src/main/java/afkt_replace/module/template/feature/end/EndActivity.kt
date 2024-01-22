package afkt_replace.module.template.feature.end

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.base.controller.ui.theme.defaultActivityBlankUITheme
import afkt_replace.core.project.router.module.template.TemplateRouter
import afkt_replace.module.template.BR
import afkt_replace.module.template.R
import afkt_replace.module.template.databinding.TemplateActivityEndBinding
import androidx.navigation.fragment.NavHostFragment
import com.therouter.router.Route

@Route(path = TemplateRouter.PATH_END_ACTIVITY)
class EndActivity : BaseAppActivity<TemplateActivityEndBinding, EndViewModel>(
    R.layout.template_activity_end, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.EndFragment, it.intent.extras)
    }
)