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
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Pagination
import kotlinx.coroutines.*
//
///**
// * Fragment to display list of results for query from search bar. Takes search query as argument
// * and calls API to populate view with results.
// */
//class ArtworkSearchView : Fragment(), CoroutineScope by MainScope() {
//
//    private var currentPage: Int = 1
//    private var searchQuery = ""
//    private var pagination =
//        Pagination(0, 25, 0, 1)
//    private var artworkIds: List<ArtworkId> = mutableListOf()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.artwork_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val args: ArtworkSearchViewArgs by navArgs()
//        // Get search query entered in search bar. Update page title display and save query to use
//        // in API call.
//        searchQuery = args.SearchQuery
//        // Populate header with search query
//        view.findViewById<TextView>(R.id.departmentNameText).text = "Showing results for \"$searchQuery\""
//
//        view.findViewById<ImageButton>(R.id.pageForwardButton).setOnClickListener{
//            currentPage++
//            launch{ loadPageContent() }
//        }
//        view.findViewById<ImageButton>(R.id.pageBackButton).setOnClickListener{
//            currentPage--
//            launch{ loadPageContent() }
//        }
//
//        launch{ loadPageContent() }
//    }
//
//    /**
//     * Updates text for pagination and disables arrow buttons when they've reached
//     * paging bounds.
//     */
//    private fun updatePaginationDisplay() {
//        val pageBackButton = view?.findViewById<ImageButton>(R.id.pageBackButton)
//        val pageForwardButton = view?.findViewById<ImageButton>(R.id.pageForwardButton)
//        val paginationText = view?.findViewById<TextView>(R.id.paginationText)
//
//        pageBackButton?.isClickable = pagination.currentPage > 1
//        pageForwardButton?.isClickable = pagination.currentPage < pagination.totalPages
//        paginationText?.text = "Page ${pagination.currentPage} of ${pagination.totalPages}"
//    }
//
//    /**
//     *  Calls API to get a page of results for the entered search query.
//     *  Called once when the view is loaded with SafeArgs and additionally when a page button is
//     *  touched to update the content for the next page.
//     */
//    private suspend fun loadPageContent() {
//        coroutineScope {
//            launch(Dispatchers.Main) {
//                try {
//                    val response =
//                        AICDataManager.apiClient.searchArtwork(searchQuery, currentPage)
//                    if (response.isSuccessful && response.body() != null) {
//                        // Get current page number and total pages from API call, update the
//                        // pagination display.
//                        response.body()!!.pagination.let { pagination = it }
//                        updatePaginationDisplay()
//                        // API returns a list of artwork IDs. These have the title and an ID that
//                        // is passed to ArtworkView to get all of an artwork's details.
//                        response.body()!!.ids.let { artworkIds = it }
//                    }
//                    val artworkSearchView =
//                        view?.findViewById(R.id.artworkSearchView) as RecyclerView
//                    // Bind data to RecyclerView elements with adapter and set layout
//                    artworkSearchView.apply {
//                        layoutManager = LinearLayoutManager(activity)
//                        adapter = ArtworkSearchAdapter(artworkIds)
//                        addItemDecoration(
//                            DividerItemDecoration(
//                                this.context,
//                                DividerItemDecoration.VERTICAL
//                            )
//                        )
//                    }
//                } catch (e: Exception) {
//                    Log.e("error", e.message.toString())
//                }
//            }
//        }
//    }
//}
//
