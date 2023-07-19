package afkt_replace.module.movie

import afkt_replace.core.lib.base.app.BaseViewModel

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : BaseViewModel()