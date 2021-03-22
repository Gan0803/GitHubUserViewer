package gan0803.pj.githubuserviewer.api

import retrofit2.Response
import retrofit2.Retrofit

class GitHubApi(private val retrofit: Retrofit) {
    fun listUsers(query: String): Response<Array<UsersResponse>> {
        val service = retrofit.create(GithubInterface::class.java)
        return service.listUsers(query).execute()
    }
}