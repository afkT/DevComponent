package afkt_replace.module.person.data.api

import afkt_replace.core.lib.bean.person.PersonActing
import afkt_replace.core.lib.bean.person.PersonDetails
import afkt_replace.core.lib.bean.person.PopularPerson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * detail: Person API Service
 * @author Ttt
 */
interface PersonService {

    @GET("/3/person/popular")
    suspend fun getPopularPerson(@Query("page") page: Int): PopularPerson

    @GET("/3/person/{person_id}")
    suspend fun getPersonDetails(@Path("person_id") id: Int): PersonDetails

    @GET("/3/person/{person_id}/combined_credits")
    suspend fun getPersonActing(@Path("person_id") id: Int): PersonActing
}