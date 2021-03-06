package gan0803.pj.githubuserviewer

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class UsersScrollListener(
    val linearLayoutManager: LinearLayoutManager,
    val visibleThreshold: Int
) : RecyclerView.OnScrollListener() {

    var firstVisibleItem = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var previousTotal = 0
    private var loading = true
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = linearLayoutManager.itemCount
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

}