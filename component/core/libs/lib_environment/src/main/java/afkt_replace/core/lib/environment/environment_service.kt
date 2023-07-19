package afkt_replace.core.lib.environment

import dev.environment.annotation.Environment
import dev.environment.annotation.Module

// ===============
// = 服务器请求地址 =
// ===============

/**
 * detail: Http Base Service 服务器请求地址
 * @author Ttt
 */
internal class HttpService private constructor() {

    // ==========
    // = Module =
    // ==========

    @Module(alias = "Splash Module")
    private inner class Splash {
        @Environment(value = "https://api.themoviedb.org", isRelease = true, alias = "release")
        private val release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "debug")
        private val debug: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "pre-release")
        private val pre_release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "development")
        private val development: String? = null
    }

    @Module(alias = "TMDB Movie Module")
    private inner class Movie {
        @Environment(value = "https://api.themoviedb.org", isRelease = true, alias = "release")
        private val release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "debug")
        private val debug: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "pre-release")
        private val pre_release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "development")
        private val development: String? = null
    }

    @Module(alias = "TMDB Person Module")
    private inner class Person {
        @Environment(value = "https://api.themoviedb.org", isRelease = true, alias = "release")
        private val release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "debug")
        private val debug: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "pre-release")
        private val pre_release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "development")
        private val development: String? = null
    }

    @Module(alias = "TMDB TV Module")
    private inner class Tv {
        @Environment(value = "https://api.themoviedb.org", isRelease = true, alias = "release")
        private val release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "debug")
        private val debug: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "pre-release")
        private val pre_release: String? = null

        @Environment(value = "https://api.themoviedb.org", alias = "development")
        private val development: String? = null
    }
}