package com.example.aicvirtualtour.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicvirtualtour.*
import com.example.aicvirtualtour.data.ResponseState
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Pagination
import com.example.aicvirtualtour.viewModels.ArtworkListEvent.*
import com.example.aicvirtualtour.viewModels.ArtworkListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This Fragment displays this list of artwork from a selected department with 25 elements per page.
 * When a department is selected, ArtworkListView takes its name and id as SafeArgs.
 * Name is displayed at the top of the list and the department's id is used for the
 * getArtworksInDepartment API call.
 */
@AndroidEntryPoint
class ArtworkListFragment : Fragment() {

    private val viewModel: ArtworkListViewModel by viewModels()
    private lateinit var artworkList: RecyclerView
    private var listAdapter = ArtworkListAdapter(emptyList())

    private var currentPage: Int = 1

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artwork_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artworkList = view.findViewById(R.id.artworkList)

        // Get arguments passed from DepartmentListView
        val args: ArtworkListFragmentArgs by navArgs()

//        if (args.isSearchView) {
//            view.findViewById<TextView>(R.id.departmentNameText).text = searchArgs.searchQuery
//            viewModel.searchQuery = searchArgs.searchQuery
//            isSearchView = true
//        } else {
//            view.findViewById<TextView>(R.id.departmentNameText).text = args.departmentTitle
//            viewModel.departmentId = args.departmentId
//        }

        // Set the page title from the navArgs
        view.findViewById<TextView>(R.id.departmentNameText).text = args.departmentTitle
        // Set the department ID for the view model so it can use it for the network call
        if (!args.isSearchView) {
            viewModel.departmentId = args.departmentId
        } else {
            viewModel.searchQuery = args.departmentTitle
        }

        view.findViewById<ImageButton>(R.id.pageForwardButton).setOnClickListener{
            viewModel.pageForward(args.isSearchView)
        }
        view.findViewById<ImageButton>(R.id.pageBackButton).setOnClickListener{
            viewModel.pageBack(args.isSearchView)
        }

        setupObservers()
        setupArtworkList()

        if (args.isSearchView) {
            viewModel.performEvent(SearchArtwork)
        } else {
            viewModel.performEvent(GetArtworksInDepartment)
        }
    }

    private fun setupObservers() {
        viewModel.responseState.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is ResponseState.Success<Set<Any>> -> {
                    displayProgressBar(false)
                    updatePaginationDisplay(response.data.elementAt(0) as Pagination)
                    (response.data.elementAt(1) as? List<ArtworkId>)?.let {
                        listAdapter.setItems(it)
                    }
                }
                is ResponseState.Error ->
                    // TODO: Change this to say artwork, put in strings.xml
                    Toast.makeText(requireContext(), "Error receiving departments. Please try again later.", Toast.LENGTH_SHORT).show()
                is ResponseState.Loading ->
                    displayProgressBar(true)
            }
        })
    }

    /**
     * Bind data to RecyclerView elements with adapter and set layout
     */
    private fun setupArtworkList() {
        artworkList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    /**
     * Updates text for pagination and disables arrow buttons when they've reached
     * paging bounds.
     */
    private fun updatePaginationDisplay(pagination: Pagination) {
        val pageBackButton = view?.findViewById<ImageButton>(R.id.pageBackButton)
        val pageForwardButton = view?.findViewById<ImageButton>(R.id.pageForwardButton)
        val paginationText = view?.findViewById<TextView>(R.id.paginationText)

        pageBackButton?.isClickable = pagination.currentPage > 1
        pageForwardButton?.isClickable = pagination.currentPage < pagination.totalPages
        paginationText?.text = getString(R.string.pagination_text, pagination.currentPage, pagination.totalPages)
    }

    private fun displayProgressBar(shouldShow: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar?.visibility = if(shouldShow) View.VISIBLE else View.GONE
    }
}