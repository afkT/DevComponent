package afkt_replace.module.person.feature.details

import afkt_replace.core.base.app.BaseAppActivity
import afkt_replace.core.base.controller.ui.theme.defaultActivityBlankUITheme
import afkt_replace.core.project.router.module.person.PersonRouter
import afkt_replace.module.person.BR
import afkt_replace.module.person.PersonViewModel
import afkt_replace.module.person.R
import afkt_replace.module.person.databinding.PersonActivityDetailsBinding
import androidx.navigation.fragment.NavHostFragment
import com.therouter.router.Route

@Route(path = PersonRouter.PATH_PERSON_DETAILS_ACTIVITY)
class PersonDetailsActivity : BaseAppActivity<PersonActivityDetailsBinding, PersonViewModel>(
    R.layout.person_activity_details, BR.viewModel, simple_UITheme = {
        it.defaultActivityBlankUITheme()
    }, simple_Agile = {
        val fragment = it.supportFragmentManager.findFragmentById(
            it.binding.navContainer.id
        ) as NavHostFragment
        val navController = fragment.navController
        navController.navigate(R.id.PersonDetailsFragment, it.intent.extras)
    }
)