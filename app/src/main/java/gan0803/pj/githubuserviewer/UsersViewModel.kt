package gan0803.pj.githubuserviewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import gan0803.pj.githubuserviewer.api.GitHubApi
import gan0803.pj.githubuserviewer.api.GithubRetrofitProvider
import gan0803.pj.githubuserviewer.model.DataSource
import gan0803.pj.githubuserviewer.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(val dataSource: DataSource) : ViewModel() {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val provider: GithubRetrofitProvider = GithubRetrofitProvider()
    private val github: GitHubApi = GitHubApi(provider.retrofit)
    val usersLiveData: LiveData<List<User>> = dataSource.getUsers()

    fun listUsers(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            github.listUsers(query).also { response ->
                if (response.isSuccessful) {
                    Log.d(TAG, "response is success")
                    val userList = response.body()!!
                        .map { user -> User(user.id, user.login, user.avatar_url, user.html_url) }
                        .toList()
                    dataSource.addAllUser(userList)
                } else {
                    Log.d(TAG, "response is failure")
                }
            }
        }
    }
}

class UsersViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UsersViewModel(dataSource = DataSource.getDataSource()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
