package gan0803.pj.githubuserviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gan0803.pj.githubuserviewer.model.User

class UsersActivity : AppCompatActivity() {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    private val usersViewModel by viewModels<UsersViewModel> {
        UsersViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userListAdapter = UserItemRecyclerViewAdapter { user -> adapterOnClick(user) }
        usersViewModel.usersLiveData.observe(this, {
            it?.let {
                Log.d(TAG, "users: {${it.toString()}}")
                userListAdapter.submitList(it as MutableList<User>)
            }
        })

        usersViewModel.listUsers("")

        val recyclerView = findViewById<RecyclerView>(R.id.users_recyclerview)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = userListAdapter
        }
    }

    private fun adapterOnClick(user: User) {
        Log.d(TAG, "list item is clicked")
    }
}