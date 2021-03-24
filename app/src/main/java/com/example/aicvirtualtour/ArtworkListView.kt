package com.example.aicvirtualtour

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ArtworkListView : Fragment(), CoroutineScope by MainScope() {

    private var currentPage: Int = 1
    private var departmentId: String = ""
    private var pagination = Pagination(0, 25, 0, 1)
    private var artworkIds: List<ArtworkId> = mutableListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artwork_list_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ArtworkListViewArgs by navArgs()
        departmentId = args.DepartmentId
        view.findViewById<TextView>(R.id.departmentNameText).text = args.DepartmentTitle
        view.findViewById<ImageButton>(R.id.pageForwardButton).setOnClickListener{
            currentPage++
            launch{ loadPageContent() }
        }
        view.findViewById<ImageButton>(R.id.pageBackButton).setOnClickListener{
            currentPage--
            launch{ loadPageContent() }
        }

        launch{ loadPageContent() }
    }

    /**
     * Updates text for pagination and disables arrow buttons when they've reached
     * paging bounds.
     */
    private fun updatePaginationDisplay() {
        val pageBackButton = view?.findViewById<ImageButton>(R.id.pageBackButton)
        val pageForwardButton = view?.findViewById<ImageButton>(R.id.pageForwardButton)
        val paginationText = view?.findViewById<TextView>(R.id.paginationText)

        pageBackButton?.isClickable = pagination.currentPage > 1
        pageForwardButton?.isClickable = pagination.currentPage < pagination.totalPages
        paginationText?.text = "Page ${pagination.currentPage} of ${pagination.totalPages}"
    }

    /**
     *  Runs API call to get a page of results for the current department.
     *  Called once when the view is loaded and every time a page button is touched to
     *  update the content for the next page
     */
    private suspend fun loadPageContent() {
        coroutineScope {
            launch(Dispatchers.Main) {
                try {
                    val response =
                        AICDataManager.apiClient.getArtworksInDepartment(departmentId, currentPage)
                    if (response.isSuccessful && response.body() != null) {
                        response.body()!!.pagination.let { pagination = it }
                        updatePaginationDisplay()
                        response.body()!!.ids.let { artworkIds = it }
                    }
                    val artworkListView =
                        view?.findViewById(R.id.artworkSearchView) as RecyclerView
                    artworkListView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ArtworkListAdapter(artworkIds)
                        addItemDecoration(
                            DividerItemDecoration(
                                this.context,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                } catch (e: Exception) {
                    Log.e("error:", e.message.toString())
                }
            }
        }
    }
}