package gan0803.pj.githubuserviewer.api

data class UsersResponse(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val html_url: String,
)