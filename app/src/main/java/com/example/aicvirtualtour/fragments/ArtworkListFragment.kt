package com.example.aicvirtualtour.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aicvirtualtour.*
import com.example.aicvirtualtour.data.Result
import com.example.aicvirtualtour.databinding.ArtworkListBinding
import com.example.aicvirtualtour.helper.autoCleared
import com.example.aicvirtualtour.models.ArtworkId
import com.example.aicvirtualtour.models.Pagination
import com.example.aicvirtualtour.viewModels.ArtworkListViewModel
import kotlinx.coroutines.*

/**
 * This Fragment displays this list of artwork from a selected department with 25 elements per page.
 * When a department is selected, ArtworkListView takes its name and id as SafeArgs.
 * Name is displayed at the top of the list and the department's id is used for the
 * getArtworksInDepartment API call.
 */
class ArtworkListFragment : Fragment() {

    private val binding: ArtworkListBinding by autoCleared()
    private val viewModel: ArtworkListViewModel by viewModels()
    private lateinit var adapter: ArtworkListAdapter

    // TODO: are these all needed?
    private var currentPage: Int = 1
    private var pagination =
        Pagination(0, 25, 0, 1)
    private var artworkIds: List<ArtworkId> = mutableListOf()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artwork_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments passed from DepartmentListView
        val args: ArtworkListFragmentArgs by navArgs()
        // Set the page title from the navArgs
        view.findViewById<TextView>(R.id.departmentNameText).text = args.DepartmentTitle
        // Set the department ID for the view model so it can use it for the network call
        viewModel.departmentId = args.DepartmentId

        view.findViewById<ImageButton>(R.id.pageForwardButton).setOnClickListener{
            viewModel.pageForward()
            updatePaginationDisplay()
        }
        view.findViewById<ImageButton>(R.id.pageBackButton).setOnClickListener{
            viewModel.pageBack()
            updatePaginationDisplay()
        }

        setupArtworkList()
        setupObservers()
    }

    /**
     * Bind data to RecyclerView elements with adapter and set layout
     */
    private fun setupArtworkList() {
        binding.artworkList.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setupObservers() {
        viewModel.artworkIds?.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    adapter = ArtworkListAdapter(it.data!!)
                }
                Result.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
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
        paginationText?.text = getString(R.string.pagination_text, pagination.currentPage, pagination.totalPages)
    }
}