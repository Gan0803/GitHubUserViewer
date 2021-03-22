package gan0803.pj.githubuserviewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gan0803.pj.githubuserviewer.model.User

class UserItemRecyclerViewAdapter(
    private val onClick: (User) -> Unit
) : ListAdapter<User, UserItemRecyclerViewAdapter.UserItemViewHolder>(UserDiffCallback) {

    class UserItemViewHolder(itemView: View, val onClick: (User) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val userName: TextView = itemView.findViewById(R.id.name)
        private val avatar: ImageView = itemView.findViewById(R.id.avatar)
        private var currentUser: User? = null

        init {
            itemView.setOnClickListener {
                currentUser?.let {
                    onClick(it)
                }
            }
        }

        fun bind(user: User) {
            currentUser = user
            userName.text = user.login
            if (user.avatarUrl != "") {
                Glide.with(itemView).load(user.avatarUrl).into(avatar)
            } else {
                avatar.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return UserItemViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

}

object UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}
