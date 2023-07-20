package afkt_replace.module.splash.feature

import afkt_replace.core.base.app.BaseAppFragment
import afkt_replace.module.splash.BR
import afkt_replace.module.splash.R
import afkt_replace.module.splash.SplashViewModel
import afkt_replace.module.splash.databinding.SplashFragmentBinding

class SplashFragment : BaseAppFragment<SplashFragmentBinding, SplashViewModel>(
    R.layout.splash_fragment, BR.viewModel
)