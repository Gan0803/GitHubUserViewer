package gan0803.pj.githubuserviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class UsersActivity : AppCompatActivity() {
    private val usersViewModel by viewModels<UsersViewModel> {
        UsersViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usersViewModel.listUsers("")
    }


}