package gan0803.pj.githubuserviewer.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource {
    companion object {
        private val TAG = this::class.java.simpleName
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

    private val usersLiveData = MutableLiveData<List<User>>()

    fun getUsers(): LiveData<List<User>> {
        return usersLiveData
    }

    fun addAllUser(userList: List<User>) {
        val currentList: List<User>? = usersLiveData.value
        Log.d(TAG, "size: ${currentList?.size}")
        if (currentList == null) {
            usersLiveData.postValue(userList)
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.addAll(userList)
            usersLiveData.postValue(updatedList)
        }
    }
}