package com.example.aicvirtualtour

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ArtworkSearchView : Fragment(), CoroutineScope by MainScope() {

    private var currentPage: Int = 1
    private var searchQuery = ""
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
        val args: ArtworkSearchViewArgs by navArgs()
        searchQuery = args.SearchQuery
        view.findViewById<TextView>(R.id.departmentNameText).text = "Showing results for \"$searchQuery\""
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

    private fun updatePaginationDisplay() {
        val pageBackButton = view?.findViewById<ImageButton>(R.id.pageBackButton)
        val paginationText = view?.findViewById<TextView>(R.id.paginationText)

        pageBackButton?.isClickable = pagination.currentPage > 1
        paginationText?.text = "Page ${pagination.currentPage} of ${pagination.totalPages}"
    }

    private suspend fun loadPageContent() {
        coroutineScope {
            launch(Dispatchers.Main) {
                try {
                    val response =
                        AICDataManager.apiClient.searchArtwork(searchQuery, currentPage)
                    if (response.isSuccessful && response.body() != null) {
                        response.body()!!.pagination.let { pagination = it }
                        updatePaginationDisplay()
                        response.body()!!.ids.let { artworkIds = it }
                    }
                    val artworkSearchView =
                        view?.findViewById(R.id.artworkSearchView) as RecyclerView
                    artworkSearchView.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ArtworkSearchAdapter(artworkIds)
                        addItemDecoration(
                            DividerItemDecoration(
                                this.context,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }
                } catch (e: Exception) {
                    Log.e("error", e.message.toString())
                }
            }
        }
    }
}

