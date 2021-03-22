package gan0803.pj.githubuserviewer.api

import retrofit2.Response
import retrofit2.Retrofit

class GitHubApi(private val retrofit: Retrofit) {
    companion object {
        const val USER_NUM_PER_PAGE: Int = 100
    }

    fun listUsers(since: Int): Response<Array<UsersResponse>> {
        val service = retrofit.create(GithubInterface::class.java)
        return service.listUsers(since, USER_NUM_PER_PAGE).execute()
    }
}