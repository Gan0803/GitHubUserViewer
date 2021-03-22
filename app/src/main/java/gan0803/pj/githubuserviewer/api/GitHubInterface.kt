package gan0803.pj.githubuserviewer.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubInterface {
    @GET("/users")
    fun listUsers(@Query("q") query: String): Call<Array<UsersResponse>>
}