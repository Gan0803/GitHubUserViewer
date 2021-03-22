package gan0803.pj.githubuserviewer.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubInterface {
    @GET("/users")
    fun listUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Call<Array<UsersResponse>>
}