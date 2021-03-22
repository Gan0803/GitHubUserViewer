package gan0803.pj.githubuserviewer

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

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

        usersViewModel.usersLiveData.observe(this, {
            it?.let {
                Log.d(TAG, "users: {${it.toString()}}")
            }
        })

        usersViewModel.listUsers("")
    }


}