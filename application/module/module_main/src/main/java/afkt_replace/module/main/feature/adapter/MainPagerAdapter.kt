package afkt_replace.module.main.feature.adapter

import afkt_replace.core.lib.router.module.movie.MovieNav
import afkt_replace.core.lib.router.module.movie.MovieRouter
import afkt_replace.core.lib.router.module.person.PersonNav
import afkt_replace.core.lib.router.module.person.PersonRouter
import afkt_replace.core.lib.router.module.tv.TvNav
import afkt_replace.core.lib.router.module.tv.TvRouter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainPagerAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                MovieNav.build(
                    MovieRouter.PATH_POPULAR_FRAGMENT
                ).navigation() as Fragment
            }

            1 -> {
                TvNav.build(
                    TvRouter.PATH_POPULAR_FRAGMENT
                ).navigation() as Fragment
            }

            2 -> {
                PersonNav.build(
                    PersonRouter.PATH_POPULAR_FRAGMENT
                ).navigation() as Fragment
            }

            else -> {
                MovieNav.build(
                    MovieRouter.PATH_POPULAR_FRAGMENT
                ).navigation() as Fragment
            }
        }
    }
}