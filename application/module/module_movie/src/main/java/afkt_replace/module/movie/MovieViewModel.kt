package afkt_replace.module.movie

import afkt_replace.core.base.app.BaseViewModel

class MovieViewModel(
    private val repository: MovieRepository = MovieRepository()
) : afkt_replace.core.base.app.BaseViewModel()