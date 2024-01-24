package afkt_replace.module.person.feature.details

import afkt_replace.core.lib.base.app.extension.loading.BaseLoadingSkeletonFragment
import afkt_replace.core.lib.router.module.person.PersonRouter
import afkt_replace.module.person.BR
import afkt_replace.module.person.R
import afkt_replace.module.person.databinding.PersonFragmentDetailsBinding
import com.therouter.router.Route

@Route(path = PersonRouter.PATH_PERSON_DETAILS_FRAGMENT)
class PersonDetailsFragment : BaseLoadingSkeletonFragment<PersonFragmentDetailsBinding, PersonDetailsViewModel>(
    R.layout.person_fragment_details, BR.viewModel, simple_Agile = {
        // 初始化 PersonDetailsFragment ViewModel 调用
        it.viewModel.initializePersonDetailsFragment(it as PersonDetailsFragment)
    }
)